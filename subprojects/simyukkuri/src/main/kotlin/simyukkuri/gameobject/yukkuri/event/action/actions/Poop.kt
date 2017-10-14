package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.MultipleAction
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

/** うんうんをするアクション. ゲスの場合うんうんをした後ふりふりする. */
class Poop(self: YukkuriStats) : MultipleAction() {
    override var currentAction: Action = PoopImpl(self)
}

/** うんうんをするアクション. ゲスの場合うんうんをした後ふりふりする. */
private class PoopImpl(val self: YukkuriStats) : Action {
    override var hasEnded: Boolean = false

    /** うんうん一回あたりにかかる時間 */
    private val unitPoopTime = 2f
    private var elapsedPoopTime = 0f

    override var currentAction: Action = this
        private set

    override fun execute() {
        self.says(self.msgList.shitting, Time.UNIT)
        elapsedPoopTime += Time.UNIT
        if (elapsedPoopTime < unitPoopTime)
            return

        self.pooParam -= self.unitPoo
        elapsedPoopTime = 0f

        if (self.isBaby) {
            self.isDirty = true
            self.feels(Emotion.Happiness.SAD)
        }
        if (self.hasWrapper) {
            self.isDirty = true
            self.feels(Emotion.Happiness.SAD)
        } else {
            // TODO: うんうんをSceneに追加する処理.
        }

        if (self.pooParam < self.unitPoo)
            hasEnded = true
        if (self.willFurifuri())
            currentAction = Furifuri()
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Poop

    override val posture: Posture
        get() = Posture.SHOW_ANUS
}