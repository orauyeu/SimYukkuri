package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.MultipleAction
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.randomStandingPosture
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** 眠るアクション */
class Sleep(self: YukkuriStat) : MultipleAction() {
    override var currentAction: Action = SleepImpl(self)
}


class SleepImpl(val self: YukkuriStat, override val posture: Posture = randomStandingPosture()) : Action {
    override var hasEnded: Boolean = false

    override var currentAction: Action = this
        private set

    // TODO: 眠くても朝になれば起きるようにする.
    override fun execute() {
        // 起きている時間は眠っている時間の倍
        self.sleepiness -= Time.UNIT * 2
        self.damageParam -= Time.UNIT

        if (self.message != null && Math.random() < 0.1 * Time.UNIT)
            self.says(self.messages.sleep, 2f)

        if (self.sleepiness <= 0) {
            hasEnded = true
            currentAction = Say(self, self.messages.wakeUp)
        }
    }

    override fun interrupt() {
        self.says(self.messages.wakeUp, 2f)
    }

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Sleep
}