package simyukkuri.gameobject.yukkuri.ai

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** ゆっくりの個別イベントを受け取って実行するクラス */
class EventManager(val self: YukkuriStats) {
    /** イベントを継続する. */
    fun proceed() {
        self.event.execute()
        self.action = self.event.currentAction
    }

    /** 指定されたイベントを継続または新たに実行する. */
    fun execute(event: IndividualEvent) {
        if (self.event.isTheSameAs(event)) {
            self.event.execute()
        } else {
            self.event.interrupt()
            self.event = event
            event.execute()
        }
    }
}