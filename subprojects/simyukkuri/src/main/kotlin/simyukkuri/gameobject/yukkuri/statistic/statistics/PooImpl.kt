package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * [Poo]の標準的ゆっくりへの実装
 */
class PooImpl : Poo {
    lateinit var self: YukkuriStats

    override var pooParam = 0f
        set(value) {
            field = when {
                value <= 0f -> 0f
                else -> value
            }
        }

    override val wantToPoo: Boolean
        get() = pooParam >= self.unitPoo

    override fun update() {
        pooParam += when {
            self.fullnessGrade >= Fullness.Grade.NORMAL -> Time.UNIT
            else -> 0.5f * Time.UNIT
        }
    }
}
