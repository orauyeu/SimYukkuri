package simyukkuri.gameobject.yukkuri.event.action

/** 単独アクションの抽象クラス. */
abstract class SingleAction : Action {
    override var hasEnded: Boolean = false
    override final val currentAction: Action
        get() = this
}