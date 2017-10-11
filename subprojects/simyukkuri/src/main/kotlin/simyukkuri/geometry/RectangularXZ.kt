package simyukkuri.geometry

/**
 * 位置と大きさを持ったxz方向に広がる長方形の物体.
 *
 * @property position 中央の座標
 * @property rectangle 直方体の実体
 */
interface RectangularXZ : HasPositionXZ, HasSizeXZ {
    val rectangle: RectangleXZ
}