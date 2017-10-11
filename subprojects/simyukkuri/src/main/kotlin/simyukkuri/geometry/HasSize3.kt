package simyukkuri.geometry


/**
 * 3次元の大きさを持った物体.
 *
 * @property size 横幅×高さ×奥行き
 * @property width 横幅
 * @property height 高さ
 * @property depth 奥行き
 */
interface HasSize3 {
    val size: Size3

    val width: Double
        get() = size.width
    val height: Double
        get() = size.height
    val depth: Double
        get() = size.depth
}
