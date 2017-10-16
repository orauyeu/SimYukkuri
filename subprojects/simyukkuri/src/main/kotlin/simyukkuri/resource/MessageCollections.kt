package simyukkuri.resource

import Root
import messageutil.*

// TODO: ダメージ有りのメッセージが欠けているときはダメージなしで代用するなどの処理を行う.
/** 指定された名前のゆっくりのメッセージを外部ファイルから読み込み, 返す. */
fun loadMessageData(name: String): MessageCollection = mutableMessageCollection().also {
    val rawMsgMap = @Suppress("UNCHECKED_CAST") (Root::class.java.getResourceAsStream("messageCollection/$name.yml").use { myYaml.load(it) } as MessageCollection)
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

/** れいむのメッセージコレクション */
val reimuMessageCollection: MessageCollection by lazy { loadMessageData("reimu") }