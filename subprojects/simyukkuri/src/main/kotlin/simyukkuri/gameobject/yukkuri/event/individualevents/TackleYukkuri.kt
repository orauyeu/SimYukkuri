package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action

/** ゆっくりに体当たりしようとするイベント */
class TackleYukkuri : IndividualEvent {
    override val hasEnded: Boolean = false

    override val currentAction: Action
        get() = TODO("not implemented")

    override fun execute() {
        TODO("not implemented.")
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is TackleYukkuri
}