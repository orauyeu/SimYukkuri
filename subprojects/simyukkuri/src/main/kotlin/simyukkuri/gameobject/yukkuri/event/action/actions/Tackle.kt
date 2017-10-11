package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** 体当たりをするアクション */
class Tackle(val self: YukkuriStat, val target: YukkuriStat) : SingleAction() {
    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Tackle) return false
        return target == other.target
    }

    override val posture: Posture
        get() = postureByPosition(self, target)
}