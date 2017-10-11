package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりの睡眠のインターフェース */
interface Sleep {
    /** 眠気のパラメータ. 0以上の値を取る. */
    var sleepiness: Float

    val isSleepy: Boolean

    fun update()
}