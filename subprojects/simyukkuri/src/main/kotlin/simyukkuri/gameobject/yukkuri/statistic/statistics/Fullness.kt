package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりの満腹度のインターフェース */
interface Fullness {
    /**
     * 満腹度のパラメータ. 最大値は100. 0を下回った場合その分のダメージを受け0にリセットされる.
     */
    var fullnessParam: Float

    /** 満腹度の大まかな区切り */
    enum class Grade {
        HUNGRY, NORMAL, FULL
    }

    /** 満腹度の大まかな区切り */
    val fullnessGrade: Grade

    /** 空腹でなくなってから経過した時間 */
    val noHungerTime: Float

    fun update()
}
