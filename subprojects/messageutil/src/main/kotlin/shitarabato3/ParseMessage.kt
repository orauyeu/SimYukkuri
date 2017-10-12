package shitarabato3

import messageutil.CommentedMessageData
import messageutil.MutableCommented
import messageutil.Statistics
import messageutil.mutableCommentedMessageData
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

/**
 * したらば版形式のメッセージを読み込み, アクション名 -> (コメント, ゆっくりタイプ -> (コメント, セリフリスト)) の形式のマップを返す.
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
    var comment = ""
    var type = Statistics()

    input.bufferedReader().forEachLine {
        lineNumber += 1
        val line = it.trim { it <= ' ' }
        if (line.isEmpty()) return@forEachLine

        val first = line[0]
        if (first == '#') {
            if (comment.isEmpty())
                comment = line.substring(1)
            else
                comment += "\n" + line.substring(1)
            return@forEachLine
        }

        // アクション名
        if (first == '[') {
            // 閉じタグの場合
            if (line[1] == '/') {
                if (comment.isNotEmpty()) {
                    msgData[actionName]!!.addCommentLine(comment)
                    comment = ""
                }

                actionName = ""

                if (type != Statistics())
                    if (strict)
                        throw RuntimeException("閉じられていない属性があります at $lineNumber: ${type.toSimpleString()}")
                type = Statistics()
                return@forEachLine
            }

            // 開始タグの場合
            else {
                if (actionName.isNotEmpty())
                    throw RuntimeException("閉じられていないアクションがあります at $lineNumber: $actionName")

                val openPos = 1
                val closePos = line.indexOf(']')
                actionName = line.substring(openPos, closePos)

                msgData.getOrPut(actionName) { MutableCommented(linkedMapOf()) }
                if (comment.isNotEmpty()) {
                    msgData[actionName]!!.addCommentLine(comment)
                    comment = ""
                }
                return@forEachLine
            }
        }

        // タイプ
        else if (first == '<') {
            // 閉じタグの場合
            if (line[1] == '/') {
                if (comment.isNotEmpty()) {
                    msgData[actionName]!!.body[type]!!.addCommentLine(comment)
                    comment = ""
                }

                val openPos = 2
                val closePos = line.indexOf('>')
                val attr = line.substring(openPos, closePos)
                if (!type.contains(attr))
                    if (strict)
                        throw Exception("対応する開始タグがありません at $lineNumber: $attr")
                type = type.removed(attr)
                return@forEachLine
            }

            // 開始タグの場合
            else {
                val openPos = 1
                val closePos = line.indexOf('>')
                val attr = line.substring(openPos, closePos)
                type = type.added(attr)

                if (comment.isNotEmpty()) {
                    msgData[actionName]!!.body.getOrPut(type) { MutableCommented(mutableListOf()) }
                    msgData[actionName]!!.body[type]!!.addCommentLine(comment)
                    comment = ""
                }
                return@forEachLine
            }
        }

        // メッセージ本文の場合
        else {
            if (!msgData[actionName]!!.body.containsKey(type))
                msgData[actionName]!!.body.put(type, MutableCommented(mutableListOf()))

            msgData[actionName]!!.body[type]!!.body.add(line)
        }
    }
    return msgData
}