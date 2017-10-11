package simyukkuri.geometry

/**
 * xy平面上の位置を持った物体.
 *
 * @property position 位置の実体.
 * @property x x座標
 * @property y y座標
 */
interface HasPositionXY {

    val position: PositionXY

    val x: Double
        get() = position.x
    val y: Double
        get() = position.y
}
