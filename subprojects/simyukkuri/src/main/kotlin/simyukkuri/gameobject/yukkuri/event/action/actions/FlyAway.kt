package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction

/** 吹っ飛んでいる状態. */
class FlyAway(override val posture: Posture) : SingleAction() {
    override fun execute() = Unit

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is FlyAway) return false
        return posture == other.posture
    }
}