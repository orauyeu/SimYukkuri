package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.Posture

/** アクションがないことを表すアクション */
object NoAction : Action {
    override val hasEnded: Boolean = true
    override val currentAction: Action = this
    override fun execute() = Unit
    override fun interrupt() = Unit
    override fun isTheSameAs(other: IndividualEvent): Boolean = other === this
    override val posture: Posture = Posture.FRONT
}