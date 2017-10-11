package messageutil

import kotlin.coroutines.experimental.buildSequence

/** コメント付きメッセージデータをyaml形式の文字列に変換する. */
fun messageDataToString(msgData: Map<String, Commented<Map<Type, Commented<List<String>>>>>): String {
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
                    appendln("# $line")

            appendln("$key:")

            for ((type, commentedMessages) in typeToCommentedMessages) {
                val (typeComment, messages) = commentedMessages
                if (typeComment.isNotEmpty())
                    for (line in typeComment.split("\n"))
                        appendln("  # $line")

                appendln("  $typeTag '${type.toSimpleString()}':")

                for (message in messages)
                    appendln("    - '$message'")
            }
        }
        // 最後の改行は消す.
        dropLast(1)
    }
}

/** コメント付きメッセージデータをyamlの行ごとのシーケンスに変換する. */
fun messageDataToStringSequence(msgData: Map<String, Commented<Map<Type, Commented<List<String>>>>>): Sequence<String> = buildSequence {
    var isFirst = true
    msgData.forEach { (key, commentedTypeToCommentedMessages) ->
        val (keyComment, typeToCommentedMessages) = commentedTypeToCommentedMessages
        if (isFirst)
            isFirst = false
        else
            yield("")

        if (keyComment.isNotEmpty())
            keyComment.splitToSequence("\n")
                .forEach { yield("# $it") }

        yield("$key:")

        typeToCommentedMessages.forEach { (type, commentedMessages) ->
            val (typeComment, messages) = commentedMessages
            if (typeComment.isNotEmpty())
                typeComment.splitToSequence("\n")
                    .forEach { yield("  # $it") }

            yield("  $typeTag '${type.toSimpleString()}':")

            for (message in messages)
                yield("  - '$message'")
        }
    }
}