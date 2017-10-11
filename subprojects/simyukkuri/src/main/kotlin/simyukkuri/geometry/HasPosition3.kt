package simyukkuri.geometry

/**
 * 空間上の位置を持った物体.
 *
 * @property position 位置の実体.
 * @property x x座標
 * @property y y座標
 * @property z z座標
 */
interface HasPosition3 {

    val position: Position3

    val x: Double
        get() = position.x
    val y: Double
        get() = position.y
    val z: Double
        get() = position.z


    fun sqrDistance(other: HasPosition3) =
        (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y) + (z - other.z) * (z - other.z)

    fun distance(other: HasPosition3) = Math.sqrt(sqrDistance(other))

    fun sqrDistance2(other: HasPosition3) =
        (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)

    fun distance2(other: HasPosition3) = Math.sqrt(sqrDistance2(other))
}

fun sqrDistance3(a: HasPosition3, b: HasPosition3) =
    (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z)

fun distance3(a: HasPosition3, b: HasPosition3) = Math.sqrt(sqrDistanceXZ(a, b))

fun sqrDistanceXZ(a: HasPosition3, b: HasPosition3) =
    (a.x - b.x) * (a.x - b.x) + (a.z - b.z) * (a.z - b.z)

fun distanceZZ(a: HasPosition3, b: HasPosition3) = Math.sqrt(sqrDistanceXZ(a, b))