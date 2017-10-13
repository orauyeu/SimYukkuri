package osdntogit

import messageutil.*
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

/** OSDN形式のゆっくりのデータをパラメータとセリフのペアに分割し, セリフデータを現行形式に変換する. */
fun reformatOsdnMessage(path: Path): Pair<Map<String, Any>, MessageData> =
    Files.newInputStream(path).use { reformatOsdnMessage(it) }

/** OSDN形式のゆっくりのデータをパラメータとセリフのペアに分割し, セリフデータを現行形式に変換する. */
fun reformatOsdnMessage(input: InputStream): Pair<Map<String, Any>, MessageData> {
    val yukkuriData = @Suppress("UNCHECKED_CAST") (myYaml.load(input) as Map<String, Any>)
    val params: MutableMap<String, Any> = mutableMapOf()
    var keyToDamageToGrowthToMsgs: Map<String, List<List<String>>> = emptyMap()
    for ((key, value) in yukkuriData) {
        if (key == "dialogue")
            keyToDamageToGrowthToMsgs = @Suppress("UNCHECKED_CAST") (value as Map<String, List<List<String>>>)
        else
            params.put(key, value)
    }

    val newMessageData = mutableMessageData()
    for ((rawMsgKey, damageToGrowthToMsgs) in keyToDamageToGrowthToMsgs) {
        val isImmoral: Boolean
        // RudeHogeという名前はHogeに置き換える
        val msgKey: String
        if (rawMsgKey.contains("Rude")) {
            isImmoral = true
            msgKey = rawMsgKey.substring(4)
        } else {
            isImmoral = false
            msgKey = rawMsgKey
        }

        val newCondToMsgs = newMessageData.getOrPut(msgKey) { linkedMapOf() }

        for (damageIndex in damageToGrowthToMsgs.indices) {
            val growthToMsgs = damageToGrowthToMsgs[damageIndex]

            val isDamaged = damageIndex == 1

            for (growthIndex in growthToMsgs.indices) {
                val message = growthToMsgs[growthIndex]

                val growth = when (growthIndex) {
                    0 -> Growth.BABY
                    1 -> Growth.CHILD
                    else -> Growth.ADULT
                }
                val type = Condition(growth, isImmoral, isDamaged, false, Love.ALL, false)

                newCondToMsgs.getOrPut(type) { mutableListOf() }.add(message)
            }
        }
    }
    return Pair(params, newMessageData)
}