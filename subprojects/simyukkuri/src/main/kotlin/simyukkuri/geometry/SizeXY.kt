package simyukkuri.geometry

/**
 * xy平面上の不変な大きさ.
 */
data class SizeXY(override val width: Double, override val height: Double) : HasSizeXY {

    override val size
        get() = this


    operator fun times(k: Double): SizeXY = multiply(k, k)
    operator fun div(k: Double): SizeXY = multiply(1 / k, 1 / k)


    /** 各方向に指定された値を掛けた大きさを返す. */
    fun multiply(x: Double, y: Double) = SizeXY(width * x, height * y)


    /** 奥行きを加えて3次元の大きさを作る. */
    fun insertDepth(depth: Double) = Size3(width, height, depth)
}
