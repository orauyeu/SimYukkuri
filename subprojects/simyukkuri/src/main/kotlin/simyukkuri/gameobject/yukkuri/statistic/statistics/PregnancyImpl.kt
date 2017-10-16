package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * [Pregnancy]の標準的ゆっくりへの実装.
 */
class PregnancyImpl : Pregnancy {
    lateinit var self: YukkuriStats

    override val isPregnant
        get() = babiesInWomb.size > 0

    override val babiesInWomb = mutableSetOf<YukkuriStats>()

    /** 妊娠してから経過した時間 */
    var elapsedGestationSec = 0f

    override val isInTravail: Boolean
        get() = elapsedGestationSec >= self.gestationSec - 10f

    override fun update() {
        if (!isPregnant) {
            elapsedGestationSec = 0f
        }
    }
}
