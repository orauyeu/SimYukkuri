package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.GameScene
import simyukkuri.gameobject.yukkuri.event.EventSequence
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.Bear
import simyukkuri.gameobject.yukkuri.event.action.actions.Say
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** 出産イベント */
class BearEvent(self: YukkuriStat, gameScene: GameScene) : EventSequence(arrayOf(
        Say(self, self.msgList.breeding),
        // TODO: ベッドへ行く.
        Say(self, self.msgList.breed),
        Bear(self, gameScene)
)) {
    override fun isTheSameAs(other: IndividualEvent): Boolean {
        return other is BearEvent
    }
}