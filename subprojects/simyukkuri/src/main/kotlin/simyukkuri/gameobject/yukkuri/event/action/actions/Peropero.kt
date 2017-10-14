package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * ぺーろぺーろするアクション.
 *
 * ぺーろぺーろされたゆっくりは清潔になる.
 */
class Peropero(val self: YukkuriStats, val target: YukkuriStats) : SingleAction() {
    var period = 2f
    override fun execute() {
        period -= Time.UNIT
        target.damageParam -= 2f * Time.UNIT

        if (period < 0f) {
            target.isDirty = false
            hasEnded = true
        }
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Peropero) return false
        return target == other.target
    }

    override val posture: Posture
        get() = postureByPosition(self, target)
}