package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction

/** 掴まれている状態 */
class Grabbed : SingleAction() {
    override fun execute() {
        TODO("文句を言う.")
    }

    override fun interrupt() = Unit

    override val posture: Posture
        get() = Posture.FRONT

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Grabbed
}