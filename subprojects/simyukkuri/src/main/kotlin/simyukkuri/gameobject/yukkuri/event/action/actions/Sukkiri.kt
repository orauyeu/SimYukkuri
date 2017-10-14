package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

/** すっきりをするアクション */
class Sukkiri(val self: YukkuriStats, val target: YukkuriStats) : SingleAction() {
    companion object {
        // Sukkiriedと長さを合わせるため
        val period = 3f
    }

    var period = 3f

    override fun execute() {
        period -= Time.UNIT
        self.says(self.msgList.hasSukkiried)

        if (period >= 0f) return

        hasEnded = true
        self.feels(Emotion.Happiness.HAPPY)
        self.fullnessParam -= self.fullnessConsumedBySukkiring
        self.isHorny = false

        if (self.hasWrapper) {
            self.isDirty = true
            return
        }
        if (target.hasWrapper) {
            target.isDirty = true
            return
        }

        // すっきりがきちんと行われた場合
        self.contact(target)
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Sukkiri) return false
        return target == other.target
    }

    override val posture: Posture
        get() = postureByPosition(self, target)
}