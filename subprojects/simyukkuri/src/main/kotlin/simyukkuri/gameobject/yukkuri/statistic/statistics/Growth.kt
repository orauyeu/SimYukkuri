package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりの成長のインターフェース */
interface Growth {
    val isBaby: Boolean

    val isChild: Boolean

    val isAdult: Boolean

    enum class GrowthStage {
        BABY, CHILD, ADULT
    }

    val growthStage: GrowthStage
}