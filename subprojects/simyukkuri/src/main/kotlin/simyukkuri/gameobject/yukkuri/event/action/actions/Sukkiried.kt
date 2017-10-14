package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.factory.YukkuriFactory
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

class Sukkiried(val self: YukkuriStats, val by: YukkuriStats) : SingleAction() {
    var period = Sukkiri.period
    override fun execute() {
        period -= Time.UNIT
        self.says(self.msgList.hasSukkiried)

        if (period >= 0f) return

        hasEnded = true
        self.feels(Emotion.Happiness.HAPPY)
        self.isSexuallyExcited = false
        self.fullnessParam -= self.fullnessConsumedByGettingSukkiried

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