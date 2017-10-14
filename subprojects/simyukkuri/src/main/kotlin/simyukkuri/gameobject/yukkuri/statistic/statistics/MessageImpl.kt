package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time

class MessageImpl(private val messageData: MessageData) : Message {
    override var message: String? = null

    /** メッセージ表示を終了するまでの残り時間 */
    private var messagePeriod: Float = 0f

    override fun says(message: String) {
        says(message, Time.UNIT)
    }

    override fun says(message: String, period: Float) {
        this.message = message
        messagePeriod = period
    }

    override fun update() {
        messagePeriod -= Time.UNIT
        if (messagePeriod < 0f) {
            messagePeriod = 0f
            message = null
        }
    }

    val findsFood: String = messageData["findsFood"]?.get(self.messageCondition)?.randomElement()
    val wantsFood: String = messageData["wantsFood"]?.get(self.messageCondition)?.randomElement()
    val sexuallyExcited: String = messageData["sexuallyExcited"]?.get(self.messageCondition)?.randomElement()
    val relaxed: String = messageData["relaxed"]?.get(self.messageCondition)?.randomElement()
    val furifuri: String = messageData["furifuri"]?.get(self.messageCondition)?.randomElement()
    val wakesUp: String = messageData["wakesUp"]?.get(self.messageCondition)?.randomElement()
    val sleeping: String = messageData["sleeping"]?.get(self.messageCondition)?.randomElement()
    val screams: String = messageData["screams"]?.get(self.messageCondition)?.randomElement()
    val scared: String = messageData["scared"]?.get(self.messageCondition)?.randomElement()
    val scaredAtRaper: String = messageData["scaredAtRaper"]?.get(self.messageCondition)?.randomElement()
    val alarmed: String = messageData["alarmed"]?.get(self.messageCondition)?.randomElement()
    val killedInstantly: String = messageData["killedInstantly"]?.get(self.messageCondition)?.randomElement()
    val dies: String = messageData["dies"]?.get(self.messageCondition)?.randomElement()
    val eating: String = messageData["eating"]?.get(self.messageCondition)?.randomElement()
    val eatingPoo: String = messageData["eatingPoo"]?.get(self.messageCondition)?.randomElement()
    val hasEatenBitter: String = messageData["hasEatenBitter"]?.get(self.messageCondition)?.randomElement()
    val getsFull: String = messageData["getsFull"]?.get(self.messageCondition)?.randomElement()
    val takesSweets: String = messageData["takesSweets"]?.get(self.messageCondition)?.randomElement()
    val hasSukkiried: String = messageData["hasSukkiried"]?.get(self.messageCondition)?.randomElement()
    val born: String = messageData["born"]?.get(self.messageCondition)?.randomElement()
    val startsToPoop: String = messageData["startsToPoop"]?.get(self.messageCondition)?.randomElement()
    val needsToPoop: String = messageData["needsToPoop"]?.get(self.messageCondition)?.randomElement()
    val hasPooped: String = messageData["hasPooped"]?.get(self.messageCondition)?.randomElement()
    val leaksPoo: String = messageData["leaksPoo"]?.get(self.messageCondition)?.randomElement()
    val surisuri: String = messageData["surisuri"]?.get(self.messageCondition)?.randomElement()
    val peropero: String = messageData["peropero"]?.get(self.messageCondition)?.randomElement()
    val bearing: String = messageData["bearing"]?.get(self.messageCondition)?.randomElement()
    val lamentsForAbortingBaby: String = messageData["lamentsForAbortingBaby"]?.get(self.messageCondition)?.randomElement()
    val complainsAboutPoo: String = messageData["complainsAboutPoo"]?.get(self.messageCondition)?.randomElement()
    val hungry: String = messageData["hungry"]?.get(self.messageCondition)?.randomElement()
    val losesOkazari: String = messageData["losesOkazari"]?.get(self.messageCondition)?.randomElement()
    val despisesYukkuriHavingNoOkazari: String = messageData["despisesYukkuriHavingNoOkazari"]?.get(self.messageCondition)?.randomElement()
    val saysLikeFlying: String = messageData["saysLikeFlying"]?.get(self.messageCondition)?.randomElement()
    val childDies: String = messageData["childDies"]?.get(self.messageCondition)?.randomElement()
    val partnerDies: String = messageData["partnerDies"]?.get(self.messageCondition)?.randomElement()
    val elderSisterDies: String = messageData["elderSisterDies"]?.get(self.messageCondition)?.randomElement()
    val youngerSisterDies: String = messageData["youngerSisterDies"]?.get(self.messageCondition)?.randomElement()
    val blockedByWall: String = messageData["blockedByWall"]?.get(self.messageCondition)?.randomElement()
    val getsTreasure: String = messageData["getsTreasure"]?.get(self.messageCondition)?.randomElement()
    val treasureTaken: String = messageData["treasureTaken"]?.get(self.messageCondition)?.randomElement()
    val nobinobi: String = messageData["nobinobi"]?.get(self.messageCondition)?.randomElement()
}