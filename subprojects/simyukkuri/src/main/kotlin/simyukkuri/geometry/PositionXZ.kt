package simyukkuri.geometry


/**
 * xz平面上の不変な点.
 */
data class PositionXZ(override val x: Double, override val z: Double) : HasPositionXZ {

    override val position: PositionXZ
        get() = this

    /** 各成分に指定された値を加えた点を返す. */
    fun add(x: Double, z: Double) = PositionXZ(this.x + x, this.z + z)
}
