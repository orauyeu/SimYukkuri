package simyukkuri.gameobject.yukkuri.event.action

import simyukkuri.gameobject.yukkuri.event.IndividualEvent

// TODO: はねるために描画上のずれや変形用のプロパティを持たせる.
// TODO: 描画用のプロパティは別でインターフェースを作って, Actionがそれを継承するようにする.
/**
 * ゆっくりの粒度の小さい行動のクラス. 描画用の状態の保持も受け持つ.
 *
 * アクション自身が他のアクションを呼び出す事もある.
 * 発言の付随するアクションは発言もセットになっている.
 */
interface Action : IndividualEvent {
    /**
     * このアクションから続く一連のアクションが終了しているか.
     *
     * このアクションが他のアクションを呼び出すとき, このアクション自身の終了を表すとは限らない.
     */
    override val hasEnded: Boolean

    /**
     * 現在のアクションを返す.
     *
     * このアクションが他のアクションを呼び出すとき, このアクション自身を指すとは限らない.
     */
    override val currentAction: Action

    /** 現在のアクションを1tick進める. */
    override fun execute()

    /** 現在実行しているアクションを中断する. */
    override fun interrupt()

    /** このアクションが現在呼び出しているアクションと[other]が同じ種類のアクションかを返す. */
    override fun isTheSameAs(other: IndividualEvent): Boolean

    /**
     * 体勢. 描画用のプロパティー.
     *
     * このアクションが他のアクションを呼び出しているとき, 呼び出しているアクションの[posture]を返す.
     */
    val posture: Posture
}

enum class Posture {
    LEFT, RIGHT, FRONT, BACK, SHOW_ANUS, ROLL_RIGHT, ROLL_LEFT
}