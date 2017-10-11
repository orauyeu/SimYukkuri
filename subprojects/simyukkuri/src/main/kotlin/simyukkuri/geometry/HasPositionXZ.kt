package simyukkuri.geometry

/**
 * xz平面上の位置を持った物体.
 *
 * @property position 位置の実体.
 * @property x x座標
 * @property z z座標
 */
interface HasPositionXZ {

    val position: PositionXZ

    val x: Double
        get() = position.x
    val z: Double
        get() = position.z


    fun sqrDistance(other: HasPositionXZ) = (x - other.x) * (x - other.x) + (z - other.z) * (z - other.z)
}
