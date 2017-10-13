package simyukkuri.resource

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

    fun findsFood(cond: Condition): String? = msgMap["findsFood"]?.get(stat)?.randomElement()
    fun wantsFood(cond: Condition): String? = msgMap["wantsFood"]?.get(stat)?.randomElement()
    fun sexuallyExcited(cond: Condition): String? = msgMap["sexuallyExcited"]?.get(stat)?.randomElement()
    fun relaxed(cond: Condition): String? = msgMap["relaxed"]?.get(stat)?.randomElement()
    fun furifuri(cond: Condition): String? = msgMap["furifuri"]?.get(stat)?.randomElement()
    fun wakesUp(cond: Condition): String? = msgMap["wakesUp"]?.get(stat)?.randomElement()
    fun sleeping(cond: Condition): String? = msgMap["sleeping"]?.get(stat)?.randomElement()
    fun screams(cond: Condition): String? = msgMap["screams"]?.get(stat)?.randomElement()
    fun scared(cond: Condition): String? = msgMap["scared"]?.get(stat)?.randomElement()
    fun scaredAtRaper(cond: Condition): String? = msgMap["scaredAtRaper"]?.get(stat)?.randomElement()
    fun alarmed(cond: Condition): String? = msgMap["alarmed"]?.get(stat)?.randomElement()
    fun killedInstantly(cond: Condition): String? = msgMap["killedInstantly"]?.get(stat)?.randomElement()
    fun dies(cond: Condition): String? = msgMap["dies"]?.get(stat)?.randomElement()
    fun eating(cond: Condition): String? = msgMap["eating"]?.get(stat)?.randomElement()
    fun eatingPoo(cond: Condition): String? = msgMap["eatingPoo"]?.get(stat)?.randomElement()
    fun hasEatenBitter(cond: Condition): String? = msgMap["hasEatenBitter"]?.get(stat)?.randomElement()
    fun getsFull(cond: Condition): String? = msgMap["getsFull"]?.get(stat)?.randomElement()
    fun takesSweets(cond: Condition): String? = msgMap["takesSweets"]?.get(stat)?.randomElement()
    fun hasSukkiried(cond: Condition): String? = msgMap["hasSukkiried"]?.get(stat)?.randomElement()
    fun born(cond: Condition): String? = msgMap["born"]?.get(stat)?.randomElement()
    fun startsToPoop(cond: Condition): String? = msgMap["startsToPoop"]?.get(stat)?.randomElement()
    fun needsToPoop(cond: Condition): String? = msgMap["needsToPoop"]?.get(stat)?.randomElement()
    fun hasPooped(cond: Condition): String? = msgMap["hasPooped"]?.get(stat)?.randomElement()
    fun leaksPoo(cond: Condition): String? = msgMap["leaksPoo"]?.get(stat)?.randomElement()
    fun surisuri(cond: Condition): String? = msgMap["surisuri"]?.get(stat)?.randomElement()
    fun peropero(cond: Condition): String? = msgMap["peropero"]?.get(stat)?.randomElement()
    fun bearing(cond: Condition): String? = msgMap["bearing"]?.get(stat)?.randomElement()
    fun lamentsForAbortingBaby(cond: Condition): String? = msgMap["lamentsForAbortingBaby"]?.get(stat)?.randomElement()
    fun complainsAboutPoo(cond: Condition): String? = msgMap["complainsAboutPoo"]?.get(stat)?.randomElement()
    fun hungry(cond: Condition): String? = msgMap["hungry"]?.get(stat)?.randomElement()
    fun losesOkazari(cond: Condition): String? = msgMap["losesOkazari"]?.get(stat)?.randomElement()
    fun despisesYukkuriHavingNoOkazari(cond: Condition): String? = msgMap["despisesYukkuriHavingNoOkazari"]?.get(stat)?.randomElement()
    fun saysLikeFlying(cond: Condition): String? = msgMap["saysLikeFlying"]?.get(stat)?.randomElement()
    fun childDies(cond: Condition): String? = msgMap["childDies"]?.get(stat)?.randomElement()
    fun partnerDies(cond: Condition): String? = msgMap["partnerDies"]?.get(stat)?.randomElement()
    fun elderSisterDies(cond: Condition): String? = msgMap["elderSisterDies"]?.get(stat)?.randomElement()
    fun youngerSisterDies(cond: Condition): String? = msgMap["youngerSisterDies"]?.get(stat)?.randomElement()
    fun blockedByWall(cond: Condition): String? = msgMap["blockedByWall"]?.get(stat)?.randomElement()
    fun getsTreasure(cond: Condition): String? = msgMap["getsTreasure"]?.get(stat)?.randomElement()
    fun treasureTaken(cond: Condition): String? = msgMap["treasureTaken"]?.get(stat)?.randomElement()
    fun nobinobi(cond: Condition): String? = msgMap["nobinobi"]?.get(stat)?.randomElement()
}