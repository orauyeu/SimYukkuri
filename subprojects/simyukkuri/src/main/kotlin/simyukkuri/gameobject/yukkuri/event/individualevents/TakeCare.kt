package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.gameobject.yukkuri.event.EventSequence
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.Approach
import simyukkuri.gameobject.yukkuri.event.action.actions.Peropero
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** 世話イベント */
class TakeCare(val self: YukkuriStat, val target: YukkuriStat) : EventSequence(arrayOf(
        Approach(self, target),
        Peropero(self, target)
)) {
    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is TakeCare) return false
        return target == other.target
    }
}