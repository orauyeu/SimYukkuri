package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * [Fullness]の標準的ゆっくりへの実装.
 *
 * @property self このパーツを所有するゆっくり
 */
open class FullnessImpl : Fullness {
    lateinit var self: YukkuriStats
    override var fullnessParam = 0f
        set(value) {
            field = value.limited(0f, self.maxFullness.toFloat())
        }

    override val fullnessGrade: Fullness.Grade
        get() = when {
            fullnessParam < self.fullnessThresholds[0] -> Fullness.Grade.HUNGRY
            fullnessParam < self.fullnessThresholds[1] -> Fullness.Grade.NORMAL
            else -> Fullness.Grade.FULL
        }

    override var noHungerTime = 0f

    // TODO: 餓死の実装.
    // TODO: 食べ過ぎの実装.
    override fun update() {
        var decrease = 1f
        if (self.isSexuallyExcited) {
            decrease += 0.5f
        }
        decrease += 0.5f * self.babiesInWomb.size
        fullnessParam -= decrease * Time.UNIT

        if (fullnessParam < 0) {
            // 空腹度を足す
            self.damageParam += -fullnessParam * self.damagePerHunger
        }

        if (fullnessGrade >= Fullness.Grade.NORMAL) {
            noHungerTime += Time.UNIT
        } else {
            noHungerTime = 0f
        }
    }
}
