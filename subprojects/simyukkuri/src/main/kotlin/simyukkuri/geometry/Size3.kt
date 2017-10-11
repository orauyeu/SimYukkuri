package simyukkuri.geometry

/**
 * 3次元の不変な大きさ.
 */
data class Size3(override val width: Double, override val height: Double, override val depth: Double) : HasSize3 {

    override val size
        get() = this


    operator fun times(k: Double) = multiply(k, k, k)
    operator fun div(k: Double) = multiply(1 / k, 1 / k, 1 / k)

    /** 各方向に指定された値を掛けた大きさを返す. */
    fun multiply(x: Double, y: Double, z: Double) = Size3(width * x, height * y, depth * z)


    /** xz方向の大きさを取り出す. */
    fun deleteHeight() = SizeXZ(width, depth)

    /** xy方向の大きさを取り出す. */
    fun deleteDepth() = SizeXY(width, height)
}
