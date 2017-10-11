package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/**
 * [Fullness]の標準的ゆっくりへの実装.
 *
 * @property self このパーツを所有するゆっくり
 */
open class FullnessImpl : Fullness {
    lateinit var self: YukkuriStat
    override var fullnessParam = 0f
        set(value) {
            field = value.limited(0f, 100f)
        }

    override val fullnessGrade: Fullness.Grade
        get() = when {
            fullnessParam < 20 -> Fullness.Grade.HUNGRY
            fullnessParam < 80 -> Fullness.Grade.NORMAL
            else -> Fullness.Grade.FULL
        }

    override var noHungerTime = 0f

    /** 空腹が0を下回ったとき, 下回った量1あたりに受けるダメージ */
    protected open val damageByHunger = 1f

    // TODO: 餓死の実装.
    // TODO: 食べ過ぎの実装.
    override fun update() {
        var decrease = 1f
        if (self.isHorny) {
            decrease += 0.5f
        }
        decrease += 0.5f * self.babiesInWomb.size
        fullnessParam -= decrease * Time.UNIT

        if (fullnessParam < 0) {
            // 空腹度を足す
            self.damageParam += -fullnessParam * damageByHunger
        }

        if (fullnessGrade >= Fullness.Grade.NORMAL) {
            noHungerTime += Time.UNIT
        } else {
            noHungerTime = 0f
        }
    }
}
