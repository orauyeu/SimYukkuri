package osdnto3

import messageutil.Growth
import messageutil.Love
import messageutil.Statistics
import messageutil.myYaml
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

/** OSDN形式のゆっくりのデータをパラメータとセリフのペアに分割し, セリフデータを現行形式に変換する. */
fun reformatOsdnMessage(path: Path): Pair<Map<String, Any>, Map<String, Map<Statistics, List<String>>>> =
    Files.newInputStream(path).use { reformatOsdnMessage(it) }

/** OSDN形式のゆっくりのデータをパラメータとセリフのペアに分割し, セリフデータを現行形式に変換する. */
fun reformatOsdnMessage(input: InputStream): Pair<Map<String, Any>, Map<String, Map<Statistics, List<String>>>> {
    val yukkuriData = @Suppress("UNCHECKED_CAST") (myYaml.load(input) as Map<String, Any>)
    val params: MutableMap<String, Any> = mutableMapOf()
    var messageWithDamageGrowth: Map<String, List<List<String>>> = emptyMap()
    for ((key, value) in yukkuriData) {
        if (key == "dialogue")
            messageWithDamageGrowth = @Suppress("UNCHECKED_CAST") (value as Map<String, List<List<String>>>)
        else
            params.put(key, value)
    }

    val newMessagesWithKeyType = LinkedHashMap<String, LinkedHashMap<Statistics, MutableList<String>>>()
    for ((rawMessageKey, messagesWithDamageGrowth) in messageWithDamageGrowth) {
        val isImmoral: Boolean
        // RudeHogeという名前のキーはHogeに置き換える
        val messageKey: String
        if (rawMessageKey.contains("Rude")) {
            isImmoral = true
            messageKey = rawMessageKey.substring(4)
        } else {
            isImmoral = false
            messageKey = rawMessageKey
        }

        newMessagesWithKeyType.getOrPut(messageKey) { linkedMapOf() }
        val newMessagesWithType = newMessagesWithKeyType[messageKey]!!

        for (damageIndex in messagesWithDamageGrowth.indices) {
            val messagesWithGrowth = messagesWithDamageGrowth[damageIndex]

            val isDamaged = damageIndex == 1

            for (growthIndex in messagesWithGrowth.indices) {
                val message = messagesWithGrowth[growthIndex]

                val growth = when (growthIndex) {
                    0 -> Growth.BABY
                    1 -> Growth.CHILD
                    else -> Growth.ADULT
                }
                val type = Statistics(growth, isImmoral, isDamaged, false, Love.ALL, false)

                newMessagesWithType.getOrPut(type) { mutableListOf() }
                val newMessages = newMessagesWithType[type]!!

                newMessages.add(message)
            }
        }
    }
    return Pair(params, newMessagesWithKeyType)
}