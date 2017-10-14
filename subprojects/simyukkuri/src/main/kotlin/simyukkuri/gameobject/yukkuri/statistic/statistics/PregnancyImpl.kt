package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * [Pregnancy]の標準的ゆっくりへの実装.
 *
 * @property gestationPeriod 出産までにかかる時間
 */
class PregnancyImpl(private val gestationPeriod: Int) : Pregnancy {
    lateinit var self: YukkuriStats

    override val isPregnant
        get() = babiesInWomb.size > 0

    override val babiesInWomb = mutableSetOf<YukkuriStats>()

    /** 妊娠してから経過した時間 */
    var pregnancyTime = 0f

    override val isInTravail: Boolean
        get() = pregnancyTime >= gestationPeriod - 10f

    override fun update() {
        if (!isPregnant) {
            pregnancyTime = 0f
        }
    }
}
