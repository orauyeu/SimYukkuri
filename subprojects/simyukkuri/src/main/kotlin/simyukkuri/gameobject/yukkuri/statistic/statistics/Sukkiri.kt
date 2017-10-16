package simyukkuri.gameobject.yukkuri.statistic.statistics

/** すっきりのインターフェース */
interface Sukkiri {
    /** むーらむーらしているか */
    var isSexuallyExcited: Boolean
    /** レイパーか */
    val isRaper: Boolean
    /** すっきりをしているか */
    var isSukkiring: Boolean
    /** すっきりが可能な精神状態か */
    val canTryToSukkiri: Boolean

    fun update()
}