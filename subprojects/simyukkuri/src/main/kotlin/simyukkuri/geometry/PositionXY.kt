package simyukkuri.geometry


/**
 * xy平面上の不変な点.
 */
data class PositionXY(override val x: Double, override val y: Double) : HasPositionXY {

    override val position: PositionXY
        get() = this

    /** 各成分に指定された値を加えた点を返す. */
    fun add(x: Double, y: Double) = PositionXY(this.x + x, this.y + y)
}
