package simyukkuri.gameobject.yukkuri.statistic.statistics

/** すっきりのインターフェース */
interface Sukkiri {
    /** むーらむーらしているか */
    var isHorny: Boolean
    /** レイパーか */
    val isRaper: Boolean
    /** スーパーレイパーか */
    val isSuperRaper: Boolean
    /** すっきりをしているか */
    var isSukkiring: Boolean
    /** すっきりが可能な精神状態か */
    val canTryToSukkiri: Boolean

    val fullnessConsumedBySukkiring: Float

    val fullnessConsumedByBeingSukkiried: Float

    fun update()
}