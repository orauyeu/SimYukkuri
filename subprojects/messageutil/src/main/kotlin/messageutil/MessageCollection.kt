package messageutil

import Root

/** ゆっくり一匹分のメッセージデータ */
typealias MessageCollection = Map<String, Map<Condition, List<String>>>

/** ゆっくり一匹分のコメント付きのメッセージデータ */
typealias CommentedMessageCollection = Map<String, Commented<Map<Condition, List<String>>>>

fun mutableMessageCollection() = linkedMapOf<String, LinkedHashMap<Condition, MutableList<String>>>()

/** 空のミュータブルなコメント付きメッセージデータを返す. */
fun mutableCommentedMessageCollection() = linkedMapOf<String, MutableCommented<LinkedHashMap<Condition, MutableList<String>>>>()

// JARとして配布したくなったら外部ファイルとして取ってこれるようにする.
/** OSDN版のセリフ名を変更前から変更後に移すマップ */
val osdnRenameMap: Map<String, String> by lazy {
    val renameMap = Root::class.java.getResourceAsStream("rename.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as MutableMap<String, String>)
    }
    renameMap.putAll(Root::class.java.getResourceAsStream("rename_event.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, String>)
    })
    renameMap.putAll(Root::class.java.getResourceAsStream("rename_osdn.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, String>)
    })
    renameMap
}

/** したらば版のセリフ名を変更前から変更後に移すマップ */
val shitarabaRenameMap: Map<String, String> by lazy {
    val renameMap = Root::class.java.getResourceAsStream("rename.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as MutableMap<String, String>)
    }
    renameMap.putAll(Root::class.java.getResourceAsStream("rename_event.yml").use {
        @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, String>)
    })
    renameMap
}

/** セリフ名を変更する. */
fun renameMessages(msgData: MessageCollection, renameMap: Map<String, String>): MessageCollection =
    linkedMapOf<String, Map<Condition, List<String>>>().apply {
        msgData.forEach { name, value ->
            try {
                put(renameMap[name]!!, value)
            } catch (e: KotlinNullPointerException) {
                throw RuntimeException("セリフ名\"$name\"がリネームファイルに存在しません.", e)
            }
        }
    }

/** セリフ名を変更する. */
fun renameCommentedMessages(msgData: CommentedMessageCollection, renameMap: Map<String, String>): CommentedMessageCollection =
    linkedMapOf<String, Commented<Map<Condition, List<String>>>>().apply {
        msgData.forEach { name, value ->
            try {
                put(renameMap[name]!!, value)
            } catch (e: KotlinNullPointerException) {
                throw RuntimeException("セリフ名\"$name\"がリネームファイルに存在しません.", e)
            }
        }
    }

/** コメント付きメッセージデータをyaml形式の文字列に変換する. */
fun messageCollectionToYaml(msgData: CommentedMessageCollection): String {
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
                appendln("  $condTag '${stats.toSimpleString()}':")
                for (msg in msgs)
                    appendln("  - '$msg'")
            }
        }
        // 最後の改行は消す.
        dropLast(1)
    }
}