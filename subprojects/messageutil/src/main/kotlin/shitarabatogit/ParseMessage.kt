package shitarabatogit

import messageutil.*
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

/**
 * したらば版形式のメッセージを読み込み, アクション名 -> (コメント, ゆっくりタイプ) -> セリフリスト) の形式のマップを返す.
 *
 * "<"と">"はセットになっていることを前提とする.
 *
 * @param strict falseのときタグの閉じ忘れや閉じすぎを無視する.
 */
fun parseShitarabaMessage(path: Path, strict: Boolean = true): CommentedMessageData =
    Files.newInputStream(path).use { parseShitarabaMessage(it, strict) }

/**
 * したらば版形式のメッセージを読み込み, アクション名 -> (コメント, ゆっくりタイプ -> (コメント, セリフリスト)) の形式のマップを返す.
 *
 * @param strict falseのときタグの閉じ忘れや閉じすぎを無視する.
 */
fun parseShitarabaMessage(input: InputStream, strict: Boolean = true): CommentedMessageData {
    val msgData = mutableCommentedMessageData()
    // 現在の行における状態
    var lineNumber = 0
    var actionName = ""
    val comments = mutableListOf<String>()
    var stats = defaultStats

    input.bufferedReader().forEachLine {
        lineNumber += 1
        val line = it.trim { it <= ' ' }
        if (line.isEmpty()) return@forEachLine

        val first = line[0]
        if (first == '#') {
            comments.add(line.substring(1))
            return@forEachLine
        }

        // アクション
        if (first == '[') {
            // 閉じタグの場合
            if (line[1] == '/') {
                if (actionName.isEmpty()) {
                    if (strict)
                        throw RuntimeException("対応する開始タグがありません at $lineNumber")
                    else {
                        val openPos = 2
                        val closePos = line.indexOf(']')
                        actionName = line.substring(openPos, closePos)
                    }
                }
                msgData.getOrPut(actionName) { MutableCommented(linkedMapOf()) }.commentLines.addAll(comments)
                comments.clear()
                actionName = ""

                if (stats != defaultStats)
                    if (strict)
                        throw RuntimeException("閉じられていない属性があります at $lineNumber: ${stats.toSimpleString()}")
                stats = defaultStats
                return@forEachLine
            }

            // 開始タグの場合
            else {
                if (actionName.isNotEmpty())
                    throw RuntimeException("閉じられていないアクションがあります at $lineNumber: $actionName")

                val openPos = 1
                val closePos = line.indexOf(']')
                actionName = line.substring(openPos, closePos)
                return@forEachLine
            }
        }

        // タイプ
        else if (first == '<') {
            // 閉じタグの場合
            if (line[1] == '/') {
                val openPos = 2
                val closePos = line.indexOf('>')
                val attr = line.substring(openPos, closePos)
                if (!stats.contains(attr))
                    if (strict)
                        throw Exception("対応する開始タグがありません at $lineNumber: $attr")
                stats = stats.removed(attr)
                return@forEachLine
            }

            // 開始タグの場合
            else {
                val openPos = 1
                val closePos = line.indexOf('>')
                val attr = line.substring(openPos, closePos)
                stats = stats.added(attr)
                return@forEachLine
            }
        }

        // メッセージ本文
        else {
            msgData.getOrPut(actionName) { MutableCommented(linkedMapOf()) }
                .body.getOrPut(stats) { mutableListOf<String>() }
                .add(line)
        }
    }
    if (actionName.isNotEmpty())
        throw RuntimeException("閉じられていないアクションがあります at $lineNumber: $actionName")
    return msgData
}