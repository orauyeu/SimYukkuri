package simyukkuri.gameobject.yukkuri.event.action

import simyukkuri.geometry.Position3

/** 描画上の状態のインターフェース */
interface GraphicState {
    /** 現在の位置に対する視覚上の位置のずれ幅. */
    val shift: Position3

    /**
     * 実際の縦幅に対する視覚上の縦幅の倍率.
     *
     * Canvas上での大きさへの変換はここでは行わない.
     */
    val heightMagnification: Double

    /**
     * 実際の横幅に対する視覚上の横幅の倍率.
     *
     * Canvas上での大きさへの変換はここでは行わない.
     */
    val radiusMagnification: Double

    /** 体勢 */
    val posture: Posture

    /** 表情. 特に指定がない場合はnull. */
    val face: Face?
}

enum class Face