package simyukkuri.geometry


/**
 * 空間上の不変な点.
 */
data class Position3(override val x: Double, override val y: Double, override val z: Double) : HasPosition3 {
    override val position
        get() = this

    /** 座標の各成分に指定された値を加えた点を返す. */
    fun added(x: Double, y: Double, z: Double) = Position3(this.x + x, this.y + y, this.z + z)

    /** x,z座標から[PositionXZ]を作る. */
    fun deleteY() = PositionXZ(x, z)

    /** x,y座標から[PositionXY]を作る. */
    fun deleteZ() = PositionXY(x, y)
}
