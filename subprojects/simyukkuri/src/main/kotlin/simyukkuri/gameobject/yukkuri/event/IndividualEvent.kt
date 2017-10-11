package simyukkuri.gameobject.yukkuri.event

import simyukkuri.gameobject.yukkuri.event.action.Action

/**
 * ゆっくりの個別イベントのクラス.
 */
interface IndividualEvent {
    /** このイベントが終了しているか. */
    val hasEnded: Boolean

    /** このイベントが実行している現在のアクションを返す. */
    val currentAction: Action

    /** イベントを1tick進める. */
    fun execute()

    /** イベントを中断させる. */
    fun interrupt()

    // TODO: 中断に対して反応するメソッドをreactToInterruptionのような名前で（中断の原因ごとに）追加する.

    /**
     * このイベントと指定されたイベントが同じ種類のイベントならtrue, そうでないならfalseを返す.
     *
     * 進行状態は考慮されない.
     *
     * @sample simyukkuri.gameobject.yukkuri.ai.EventManager.execute
     * @sample simyukkuri.gameobject.yukkuri.event.action.actions.Move
     */
    fun isTheSameAs(other: IndividualEvent): Boolean
}