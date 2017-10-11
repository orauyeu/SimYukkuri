package simyukkuri.geometry

/**
 * 位置と大きさを持ったxz方向に広がる長方形.
 *
 * @property position 中心の座標
 * @property size 横幅×縦幅
 */
data class RectangleXZ(override val position: PositionXZ, override val size: SizeXZ) : HasPositionXZ by position, HasSizeXZ by size {

    constructor(x: Double, z: Double, width: Double, depth: Double) : this(PositionXZ(x, z), SizeXZ(width, depth))


    /** 指定された倍率で各方向に拡大した直方体を返す. */
    fun multiplied(x: Double, z: Double) = RectangleXZ(position, size.multiply(x, z))

    /** 各方向に指定された量平行移動した直方体を返す. */
    fun translated(x: Double, z: Double) = RectangleXZ(position.add(x, z), size)
}