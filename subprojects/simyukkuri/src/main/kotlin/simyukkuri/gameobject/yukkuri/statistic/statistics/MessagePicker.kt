package simyukkuri.gameobject.yukkuri.statistic.statistics

import messageutil.MessageCollection
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.randomElement

/** メッセージを取り出すためのクラス. */
class MessagePicker(private val messageCollection: MessageCollection) {
    lateinit var self: YukkuriStats
    val findsFood: String?
        get() = messageCollection["findsFood"]?.get(self.messageCondition)?.randomElement()
    val wantsFood: String?
        get() = messageCollection["wantsFood"]?.get(self.messageCondition)?.randomElement()
    val sexuallyExcited: String?
        get() = messageCollection["sexuallyExcited"]?.get(self.messageCondition)?.randomElement()
    val relaxed: String?
        get() = messageCollection["relaxed"]?.get(self.messageCondition)?.randomElement()
    val furifuri: String?
        get() = messageCollection["furifuri"]?.get(self.messageCondition)?.randomElement()
    val wakesUp: String?
        get() = messageCollection["wakesUp"]?.get(self.messageCondition)?.randomElement()
    val sleeping: String?
        get() = messageCollection["sleeping"]?.get(self.messageCondition)?.randomElement()
    val screams: String?
        get() = messageCollection["screams"]?.get(self.messageCondition)?.randomElement()
    val scared: String?
        get() = messageCollection["scared"]?.get(self.messageCondition)?.randomElement()
    val scaredAtRaper: String?
        get() = messageCollection["scaredAtRaper"]?.get(self.messageCondition)?.randomElement()
    val alarmed: String?
        get() = messageCollection["alarmed"]?.get(self.messageCondition)?.randomElement()
    val killedInstantly: String?
        get() = messageCollection["killedInstantly"]?.get(self.messageCondition)?.randomElement()
    val dies: String?
        get() = messageCollection["dies"]?.get(self.messageCondition)?.randomElement()
    val eating: String?
        get() = messageCollection["eating"]?.get(self.messageCondition)?.randomElement()
    val eatingPoo: String?
        get() = messageCollection["eatingPoo"]?.get(self.messageCondition)?.randomElement()
    val hasEatenBitter: String?
        get() = messageCollection["hasEatenBitter"]?.get(self.messageCondition)?.randomElement()
    val getsFull: String?
        get() = messageCollection["getsFull"]?.get(self.messageCondition)?.randomElement()
    val takesSweets: String?
        get() = messageCollection["takesSweets"]?.get(self.messageCondition)?.randomElement()
    val hasSukkiried: String?
        get() = messageCollection["hasSukkiried"]?.get(self.messageCondition)?.randomElement()
    val born: String?
        get() = messageCollection["born"]?.get(self.messageCondition)?.randomElement()
    val startsToPoop: String?
        get() = messageCollection["startsToPoop"]?.get(self.messageCondition)?.randomElement()
    val needsToPoop: String?
        get() = messageCollection["needsToPoop"]?.get(self.messageCondition)?.randomElement()
    val hasPooped: String?
        get() = messageCollection["hasPooped"]?.get(self.messageCondition)?.randomElement()
    val leaksPoo: String?
        get() = messageCollection["leaksPoo"]?.get(self.messageCondition)?.randomElement()
    val surisuri: String?
        get() = messageCollection["surisuri"]?.get(self.messageCondition)?.randomElement()
    val peropero: String?
        get() = messageCollection["peropero"]?.get(self.messageCondition)?.randomElement()
    val bearing: String?
        get() = messageCollection["bearing"]?.get(self.messageCondition)?.randomElement()
    val greetsBabyAfterBearing: String?
        get() = messageCollection["greetsBabyAfterBearing"]?.get(self.messageCondition)?.randomElement()
    val lamentsForAbortingBaby: String?
        get() = messageCollection["lamentsForAbortingBaby"]?.get(self.messageCondition)?.randomElement()
    val complainsAboutPoo: String?
        get() = messageCollection["complainsAboutPoo"]?.get(self.messageCondition)?.randomElement()
    val hungry: String?
        get() = messageCollection["hungry"]?.get(self.messageCondition)?.randomElement()
    val losesOkazari: String?
        get() = messageCollection["losesOkazari"]?.get(self.messageCondition)?.randomElement()
    val despisesYukkuriHavingNoOkazari: String?
        get() = messageCollection["despisesYukkuriHavingNoOkazari"]?.get(self.messageCondition)?.randomElement()
    val saysLikeFlying: String?
        get() = messageCollection["saysLikeFlying"]?.get(self.messageCondition)?.randomElement()
    val childDies: String?
        get() = messageCollection["childDies"]?.get(self.messageCondition)?.randomElement()
    val partnerDies: String?
        get() = messageCollection["partnerDies"]?.get(self.messageCondition)?.randomElement()
    val elderSisterDies: String?
        get() = messageCollection["elderSisterDies"]?.get(self.messageCondition)?.randomElement()
    val youngerSisterDies: String?
        get() = messageCollection["youngerSisterDies"]?.get(self.messageCondition)?.randomElement()
    val blockedByWall: String?
        get() = messageCollection["blockedByWall"]?.get(self.messageCondition)?.randomElement()
    val getsTreasure: String?
        get() = messageCollection["getsTreasure"]?.get(self.messageCondition)?.randomElement()
    val treasureTaken: String?
        get() = messageCollection["treasureTaken"]?.get(self.messageCondition)?.randomElement()
    val nobinobi: String?
        get() = messageCollection["nobinobi"]?.get(self.messageCondition)?.randomElement()
}