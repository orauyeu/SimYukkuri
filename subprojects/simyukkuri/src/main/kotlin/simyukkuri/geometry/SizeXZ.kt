package simyukkuri.geometry

/**
 * xz平面上の不変な大きさ.
 */
data class SizeXZ(override val width: Double, override val depth: Double) : HasSizeXZ {

    override val size
        get() = this


    operator fun times(k: Double): SizeXZ = multiply(k, k)
    operator fun div(k: Double): SizeXZ = multiply(1 / k, 1 / k)


    /** 各方向に指定された値を掛けた大きさを返す. */
    fun multiply(x: Double, z: Double) = SizeXZ(width * x, depth * z)


    /** 高さを加えて3次元の大きさを作る. */
    fun insertHeight(height: Double) = Size3(width, height, depth)
}
