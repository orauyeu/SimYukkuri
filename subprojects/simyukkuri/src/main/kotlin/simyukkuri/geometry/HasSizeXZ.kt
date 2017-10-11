package simyukkuri.geometry

/**
 * xz方向の大きさを持った物体.
 *
 * @property size 横幅×奥行き
 * @property width 横幅
 * @property depth 奥行き
 */
interface HasSizeXZ {
    val size: SizeXZ

    val width: Double
        get() = size.width
    val depth: Double
        get() = size.depth
}
