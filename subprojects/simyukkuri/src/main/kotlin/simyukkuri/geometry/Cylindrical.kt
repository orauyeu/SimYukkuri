package simyukkuri.geometry

/**
 * 円柱状の物体.
 *
 * @property position 底面中央の座標
 * @property radius 半径
 * @property height 高さ
 */
interface Cylindrical : HasPosition3 {
    override val position: Position3
    val radius: Double
    val height: Double
}