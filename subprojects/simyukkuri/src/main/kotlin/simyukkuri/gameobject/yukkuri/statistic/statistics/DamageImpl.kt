package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * [Damage]の標準的ゆっくりへの実装.
 *
 * @property self このパーツを所有するゆっくり
 */
class DamageImpl : Damage {
    lateinit var self: YukkuriStats

    override var damageParam = 0f
        set(value) {
            field = when {
                value <= 0 -> 0f
                else -> value
            }
        }

    override val damageGrade
        get() = when {
            damageParam >= 50f -> Damage.Grade.LARGE
            else -> Damage.Grade.NONE
        }

    override var noDamageTime = 0f

    override fun update() {
        if (!self.hasDevelopedYukabi && self.fullnessGrade >= Fullness.Grade.NORMAL) {
            damageParam -= Time.UNIT
        }

        if (damageGrade == Damage.Grade.NONE) {
            noDamageTime += Time.UNIT
        } else {
            noDamageTime = 0f
        }
    }
}
