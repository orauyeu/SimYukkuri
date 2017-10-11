package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.gameobject.yukkuri.event.EventSequence
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.Poop
import simyukkuri.gameobject.yukkuri.event.action.actions.Say
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** うんうんイベント */
class PooEvent(self: YukkuriStat) : EventSequence(arrayOf(
        Say(self, self.messages.wantShit),
        // TODO: トイレに行く.
        Say(self, self.messages.shitting),
        Poop(self)
)) {
    override fun isTheSameAs(other: IndividualEvent): Boolean = other is PooEvent
}