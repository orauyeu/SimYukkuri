package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.factories.YukkuriFactory
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

class Sukkiried(val self: YukkuriStat, val by: YukkuriStat) : SingleAction() {
    var period = Sukkiri.period
    override fun execute() {
        period -= Time.UNIT
        self.says(self.msgList.sukkiri)

        if (period >= 0f) return

        hasEnded = true
        self.feels(Emotion.Happiness.HAPPY)
        self.isHorny = false
        self.fullnessParam -= self.fullnessConsumedByBeingSukkiried

        // TODO: レイパーの場合や既に番がいる場合などを考慮する.
        self.partner = by
        // TODO: 一度に複数の赤ゆを妊娠できるようにする.
        self.babiesInWomb.add(YukkuriFactory.createYukkuriFromParents(self, by))
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Sukkiried) return false
        return by == other.by
    }

    override val posture: Posture
        get() = postureByPosition(self, by)
}