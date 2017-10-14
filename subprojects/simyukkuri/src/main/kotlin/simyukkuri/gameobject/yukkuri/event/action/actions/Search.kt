package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

// TODO: 成功して終了したか, 失敗して終了したかを表せるようにする.
/**
 * 指定された秒数探し回るアクション.
 */
class Search(self: YukkuriStats, var period: Float = 30f) : SingleAction() {
    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Search

    override val posture: Posture
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}