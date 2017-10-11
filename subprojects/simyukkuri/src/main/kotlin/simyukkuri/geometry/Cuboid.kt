package simyukkuri.geometry

/** 位置と大きさを持った直方体 */
data class Cuboid(override val position: Position3, override val size: Size3) : CuboidShaped, HasPosition3 by position, HasSize3 by size {

    override val shape: Cuboid
        get() = this


    operator fun times(k: Double) = multiplied(k, k, k)
    operator fun div(k: Double) = multiplied(1 / k, 1 / k, 1 / k)

    /** 各方向に指定された量平行移動した直方体を返す. */
    fun translated(x: Double, y: Double, z: Double) = Cuboid(position.added(x, y, z), size)

    /** 底面中央を中心に指定された倍率で各方向に拡大した直方体を返す. */
    fun multiplied(x: Double, y: Double, z: Double) = Cuboid(position, size.multiply(x, y, z))


    fun copy(x: Double = this.x, y: Double = this.y, z: Double = this.z, size: Size3 = this.size): Cuboid {
        return Cuboid(Position3(x, y, z), size)
    }
}
