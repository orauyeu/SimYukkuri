package simyukkuri.gameobject.yukkuri.statistic.statistics

/** [Growth]の標準的ゆっくりへの実装 */
class GrowthImpl(override val growthStage: Growth.GrowthStage) : Growth {
    override val isBaby: Boolean = growthStage == Growth.GrowthStage.BABY
    override val isChild: Boolean = growthStage == Growth.GrowthStage.CHILD
    override val isAdult: Boolean = growthStage == Growth.GrowthStage.ADULT
}