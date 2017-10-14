package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.GameScene
import simyukkuri.gameobject.yukkuri.event.EventSequence
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.Bear
import simyukkuri.gameobject.yukkuri.event.action.actions.Say
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** 出産イベント */
class BearEvent(self: YukkuriStats, gameScene: GameScene) : EventSequence(arrayOf(
        Say(self, self.msgList.bearing),
        // TODO: ベッドへ行く.
        Say(self, self.msgList.greetsBabyAfterBearing),
        Bear(self, gameScene)
)) {
    override fun isTheSameAs(other: IndividualEvent): Boolean {
        return other is BearEvent
    }
}