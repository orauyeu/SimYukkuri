package messageutil

import java.nio.file.Files
import java.nio.file.Path

/** メッセージを保管するクラスの名前 */
val messageClassName = "Messages"

/** メッセージデータに対応する, セリフ毎にプロパティを持つクラスの文字列を生成する. */
fun messageDataToPojoString(msgData: Map<String, Any?>): String = buildString {
    appendln("class $messageClassName {")
    for ((messageName) in msgData)
        appendln("    val $messageName: Map<Statistics, List<String>>")
    append("}")
}

/** 指定されたセリフデータからMessagesクラスのソースファイルを作成し, [writeDir]に保存する. */
fun writeClassSource(writeDir: Path, vararg messagePaths: Path) {
    val srcString = run {
        when (messagePaths.size) {
            0 -> ""
            1 -> Files.newInputStream(messagePaths[0])
                .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Statistics, List<String>>>) }
                .let { messageDataToPojoString(it) }
            else -> run {
                val messageData = Files.newInputStream(messagePaths[0])
                    .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Statistics, List<String>>>) }
                for (i in 1 until messagePaths.size) {
                    Files.newInputStream(messagePaths[i])
                        .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as MessageData) }
                        .let { messageData.putAll(it) }
                }
                messageDataToPojoString(messageData)
            }
        }
    }

    val path = writeDir.resolve("$messageClassName.kt")
    Files.newBufferedWriter(path).use {
        it.write(srcString)
    }
}