package simyukkuri.resource

import Root
import messageutil.*

fun loadMessageData(name: String): MessageData = mutableMessageData().also {
    val rawMsgMap = @Suppress("UNCHECKED_CAST") (Root::class.java.getResourceAsStream("msgList/$name.yml").use { myYaml.load(it) } as MessageData)
    for ((key, condToMsgs) in rawMsgMap) {
        val tmpCondToMsgs = linkedMapOf<Condition, MutableList<String>>()
        it.put(key, tmpCondToMsgs)
        for ((rawStats, msgs) in condToMsgs) {
            if (rawStats.growth == Growth.BABY_OR_CHILD) {
                tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.BABY)) { mutableListOf() }.addAll(msgs)
                tmpCondToMsgs.getOrPut(rawStats.copy(growth = Growth.CHILD)) { mutableListOf() }.addAll(msgs)
            } else
                tmpCondToMsgs.getOrPut(rawStats) { mutableListOf() }.addAll(msgs)
        }
    }
}

val reimuMessageData: MessageData by lazy { loadMessageData("reimu") }