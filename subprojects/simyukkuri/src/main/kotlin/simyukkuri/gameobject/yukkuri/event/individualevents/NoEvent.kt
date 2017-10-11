package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.actions.NoAction

/** イベントがないことを表すイベント */
object NoEvent : IndividualEvent {
    override val currentAction: Action = NoAction
    override val hasEnded: Boolean = true
    override fun execute() = Unit
    override fun interrupt() = Unit
    override fun isTheSameAs(other: IndividualEvent): Boolean = other === this
}