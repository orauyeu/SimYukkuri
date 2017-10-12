package messageutil

import Root

/** ゆっくり一匹分のメッセージデータ */
typealias MessageData = Map<String, Map<Statistics, List<String>>>

/** ゆっくり一匹分のコメント付きのメッセージデータ */
typealias CommentedMessageData = Map<String, Commented<Map<Statistics, Commented<List<String>>>>>

/** 空のミュータブルなメッセージデータを返す. */
fun mutableCommentedMessageData() = linkedMapOf<String, MutableCommented<LinkedHashMap<Statistics, MutableCommented<MutableList<String>>>>>()

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
    linkedMapOf<String, Commented<Map<Statistics, Commented<List<String>>>>>().apply {
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
            val (keyComment, typeToCommentedMessages) = commentedTypeToCommentedMessages
            if (isFirst)
                isFirst = false
            else
                appendln()

            if (keyComment.isNotEmpty())
                for (line in keyComment.split("\n"))
                // なぜかコメントの先頭に空白が入るのでトリミング
                    appendln("# ${line.trim { it <= ' ' }}")

            appendln("$key:")

            for ((type, commentedMessages) in typeToCommentedMessages) {
                val (typeComment, messages) = commentedMessages
                if (typeComment.isNotEmpty())
                    for (line in typeComment.split("\n"))
                        appendln("  # $line")

                appendln("  $statsTag '${type.toSimpleString()}':")

                for (message in messages)
                    appendln("  - '$message'")
            }
        }
        // 最後の改行は消す.
        dropLast(1)
    }
}