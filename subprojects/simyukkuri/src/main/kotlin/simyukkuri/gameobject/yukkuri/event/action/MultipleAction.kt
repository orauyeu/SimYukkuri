package simyukkuri.gameobject.yukkuri.event.action

import simyukkuri.gameobject.yukkuri.event.IndividualEvent

/**
 * 連鎖するアクションの抽象クラス.
 *
 * @sample simyukkuri.gameobject.yukkuri.event.action.actions.Bear
 * @sample simyukkuri.gameobject.yukkuri.event.action.actions.BearImpl
 */
abstract class MultipleAction : Action {
    override val hasEnded: Boolean
        get() = currentAction.hasEnded

    abstract override var currentAction: Action

    override final fun execute() {
        currentAction.execute()
        if (currentAction.hasEnded) {
            val nextAction = currentAction.currentAction
            if (!nextAction.hasEnded)
                currentAction = nextAction
        }
    }

    override final fun interrupt() {
        currentAction.interrupt()
    }

    override final fun isTheSameAs(other: IndividualEvent): Boolean = currentAction.isTheSameAs(other)

    override final val posture: Posture
        get() = currentAction.posture
}