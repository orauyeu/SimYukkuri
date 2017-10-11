package osdnto3

import messageutil.Growth
import messageutil.Love
import messageutil.Type
import messageutil.myYaml
import java.io.InputStream

/**
 * YAML形式のゆっくりのデータをパラメータとセリフのペアに分割し,
 * セリフデータはセリフの種類, モラル, 成長段階をキーとする形式に変更する.
 *
 * 各セリフのキーに対してリストが対応する. (ランダムにリスト内のどれかをしゃべるような状況を想定している.)
 */
fun reformatOsdnMessage(input: InputStream): Pair<Map<String, Any>, Map<String, Map<Type, List<String>>>> {
    val yukkuriData = @Suppress("UNCHECKED_CAST") (myYaml.load(input) as Map<String, Any>)
    val params: MutableMap<String, Any> = mutableMapOf()
    var messageWithDamageGrowth: Map<String, List<List<String>>> = emptyMap()
    for ((key, value) in yukkuriData) {
        if (key == "dialogue")
            messageWithDamageGrowth = @Suppress("UNCHECKED_CAST") (value as Map<String, List<List<String>>>)
        else
            params.put(key, value)
    }

    val newMessagesWithKeyType = LinkedHashMap<String, LinkedHashMap<Type, MutableList<String>>>()
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
                val type = Type(growth, isImmoral, isDamaged, false, Love.ALL, false)

                newMessagesWithType.getOrPut(type) { mutableListOf() }
                val newMessages = newMessagesWithType[type]!!

                newMessages.add(message)
            }
        }
    }
    return Pair(params, newMessagesWithKeyType)
}