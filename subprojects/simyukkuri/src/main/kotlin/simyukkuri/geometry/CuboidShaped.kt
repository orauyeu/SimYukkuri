package simyukkuri.geometry

/**
 * 位置と大きさを持った直方体の形をした物体.
 *
 * @property position 底面中央の座標
 * @property shape 直方体の実体
 */
interface CuboidShaped : HasPosition3, HasSize3 {
    val shape: Cuboid
}
