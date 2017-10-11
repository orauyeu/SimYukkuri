package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.animation

/** 尻をふりふりするアクション */
class Furifuri(private var period: Float = 3f) : SingleAction() {
    override fun execute() {
        period -= Time.UNIT
        if (period < 0f)
            hasEnded = true
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Furifuri

    private val postures = arrayOf(Posture.ROLL_LEFT, Posture.ROLL_RIGHT)

    override val posture: Posture
        get() = animation(postures, 1f, period)
}