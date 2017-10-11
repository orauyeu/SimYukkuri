package simyukkuri.geometry

/**
 * 位置と大きさを持ったxy方向に広がる長方形.
 *
 * @property position 底辺中央の座標
 * @property size 横幅×縦幅
 */
data class RectangleXY(override val position: PositionXY, override val size: SizeXY) : HasPositionXY by position, HasSizeXY by size {

    constructor(x: Double, y: Double, width: Double, height: Double) : this(PositionXY(x, y), SizeXY(width, height))


    /** 左上の座標 */
    val leftTop
        get() = position.add(-width / 2, -height)

    /** 左端のx座標 */
    val minX
        get() = position.x - width / 2
    /** 上端のy座標 */
    val minY
        get() = position.y - height


    /** 底辺中央を中心に指定された倍率で各方向に拡大した直方体を返す. */
    fun multiplied(x: Double, y: Double) = RectangleXY(position, size.multiply(x, y))

    /** 各方向に指定された量平行移動した直方体を返す. */
    fun translated(x: Double, y: Double) = RectangleXY(position.add(x, y), size)
}
