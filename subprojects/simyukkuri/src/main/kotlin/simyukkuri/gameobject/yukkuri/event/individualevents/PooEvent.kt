package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.gameobject.yukkuri.event.EventSequence
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.Poop
import simyukkuri.gameobject.yukkuri.event.action.actions.Say
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** うんうんイベント */
class PooEvent(self: YukkuriStats) : EventSequence(arrayOf(
        Say(self, self.msgList.startsToPoop),
        // TODO: トイレに行く.
        Say(self, self.msgList.hasPooped),
        Poop(self)
)) {
    override fun isTheSameAs(other: IndividualEvent): Boolean = other is PooEvent
}