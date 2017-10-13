package messageutil

import java.nio.file.Files
import java.nio.file.Path

/** メッセージを保管するクラスの名前 */
val messageClassName = "Messages"

/* 編集用
abstract class Messages(name: String) {
    private val msgMap: Map<String, Map<Condition, List<String>>>

    init {
        val tmpMsgMap = linkedMapOf<String, MutableMap<Condition, MutableList<String>>>()
        val rawMsgMap = @Suppress("UNCHECKED_CAST") (Root::class.java.getResourceAsStream("messages/$name.yml").use { myYaml.load(it) } as MessageData)
        for ((key, condToMsgs) in rawMsgMap) {
            val tmpCondToMsgs = linkedMapOf<Condition, MutableList<String>>()
            tmpMsgMap.put(key, tmpCondToMsgs)
            for ((rawStats, msgs) in condToMsgs) {
                if (rawStats.growth == Growth.BABY_OR_CHILD) {
                    tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.BABY)) { mutableListOf() }.addAll(msgs)
                    tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.CHILD)) { mutableListOf() }.addAll(msgs)
                } else
                    tmpCondToMsgs.getOrPut(rawStats) { mutableListOf() }.addAll(msgs)
            }
        }
        msgMap = tmpMsgMap
    }
}
*/

// TODO: ダメージを受けている版のメッセージがない場合, 通常版のメッセージで代用するなど欠けを補う.
/** メッセージデータに対応する, セリフ毎にプロパティを持つクラスの文字列を生成する. */
fun messageDataToPojoString(msgData: Map<String, Any?>): String = buildString {
    appendln("abstract class Messages(name: String) {\n" +
            "    private val msgMap: Map<String, Map<Condition, List<String>>>\n" +
            "\n" +
            "    init {\n" +
            "        val tmpMsgMap = linkedMapOf<String, MutableMap<Condition, MutableList<String>>>()\n" +
            "        val rawMsgMap = @Suppress(\"UNCHECKED_CAST\") (Root::class.java.getResourceAsStream(\"messages/\$name.yml\").use { myYaml.load(it) } as MessageData)\n" +
            "        for ((key, condToMsgs) in rawMsgMap) {\n" +
            "            val tmpCondToMsgs = linkedMapOf<Condition, MutableList<String>>()\n" +
            "            tmpMsgMap.put(key, tmpCondToMsgs)\n" +
            "            for ((rawStats, msgs) in condToMsgs) {\n" +
            "                if (rawStats.growth == Growth.BABY_OR_CHILD) {\n" +
            "                    tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.BABY)) { mutableListOf() }.addAll(msgs)\n" +
            "                    tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.CHILD)) { mutableListOf() }.addAll(msgs)\n" +
            "                } else\n" +
            "                    tmpCondToMsgs.getOrPut(rawStats) { mutableListOf() }.addAll(msgs)\n" +
            "            }\n" +
            "        }\n" +
            "        msgMap = tmpMsgMap\n" +
            "    }" +
            "\n")
    for ((messageName) in msgData)
        appendln("    fun $messageName(cond: Condition): String? = msgMap[\"$messageName\"]?.get(stat)?.randomElement()")
    append("}")
}

/** 指定されたセリフデータからMessagesクラスのソースファイルを作成し, [writeDir]に保存する. */
fun writeClassSource(writeDir: Path, vararg messagePaths: Path) {
    val srcString = run {
        when (messagePaths.size) {
            0 -> ""
            1 -> Files.newInputStream(messagePaths[0])
                .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Condition, List<String>>>) }
                .let { messageDataToPojoString(it) }
            else -> run {
                val messageData = Files.newInputStream(messagePaths[0])
                    .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as LinkedHashMap<String, Map<Condition, List<String>>>) }
                for (i in 1 until messagePaths.size) {
                    Files.newInputStream(messagePaths[i])
                        .use { @Suppress("UNCHECKED_CAST") (myYaml.load(it) as Map<String, Map<Condition, List<String>>>) }
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