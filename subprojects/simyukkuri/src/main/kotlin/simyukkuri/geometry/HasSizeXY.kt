package simyukkuri.geometry

/**
 * xy方向の大きさを持った物体.
 *
 * @property size 横幅×高さ
 * @property width 横幅
 * @property height 高さ
 */
interface HasSizeXY {
    val size: SizeXY

    val width: Double
        get() = size.width
    val height: Double
        get() = size.height
}
