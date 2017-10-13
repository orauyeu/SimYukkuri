package messageutil

import Root

/** ゆっくり一匹分のメッセージデータ */
typealias MessageData = Map<String, Map<Statistics, List<String>>>

/** ゆっくり一匹分のコメント付きのメッセージデータ */
typealias CommentedMessageData = Map<String, Commented<Map<Statistics, List<String>>>>

fun mutableMessageData() = linkedMapOf<String, LinkedHashMap<Statistics, MutableList<String>>>()

/** 空のミュータブルなコメント付きメッセージデータを返す. */
fun mutableCommentedMessageData() = linkedMapOf<String, MutableCommented<LinkedHashMap<Statistics, MutableList<String>>>>()

// JARとして配布したくなったら外部ファイルとして取ってこれるようにする.
/** セリフ名を変更前から変更後に移すマップ */
val renameMap: Map<String, String> by lazy {
    val renameMap = Root::class.java.getResourceAsStream("rename.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as MutableMap<String, String>)
    }
    renameMap.putAll(Root::class.java.getResourceAsStream("rename_event.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, String>)
    })
    renameMap
}

/** セリフ名を変更する. */
fun renameMessages(msgData: MessageData): MessageData =
    linkedMapOf<String, Map<Statistics, List<String>>>().apply {
        msgData.forEach { name, value ->
            try {
                put(renameMap[name]!!, value)
            } catch (e: KotlinNullPointerException) {
                throw RuntimeException("セリフ名\"$name\"がリネームファイルに存在しません.", e)
            }
        }
    }

/** セリフ名を変更する. */
fun renameCommentedMessages(msgData: CommentedMessageData): CommentedMessageData =
    linkedMapOf<String, Commented<Map<Statistics, List<String>>>>().apply {
        msgData.forEach { name, value ->
            try {
                put(renameMap[name]!!, value)
            } catch (e: KotlinNullPointerException) {
                throw RuntimeException("セリフ名\"$name\"がリネームファイルに存在しません.", e)
            }
        }
    }

/** コメント付きメッセージデータをyaml形式の文字列に変換する. */
fun messageDataToString(msgData: CommentedMessageData): String {
    return buildString {
        var isFirst = true
        for ((key, commentedTypeToCommentedMessages) in msgData) {
            val (commentLines, statsToMsgs) = commentedTypeToCommentedMessages
            if (isFirst)
                isFirst = false
            else
                appendln()

            if (commentLines.isNotEmpty())
                for (line in commentLines)
                    // なぜかコメントの先頭に空白が入るのでトリミング
                    appendln("# ${line.trim { it <= ' ' }}")
            appendln("$key:")
            for ((stats, msgs) in statsToMsgs) {
                appendln("  $statsTag '${stats.toSimpleString()}':")
                for (msg in msgs)
                    appendln("  - '$msg'")
            }
        }
        // 最後の改行は消す.
        dropLast(1)
    }
}