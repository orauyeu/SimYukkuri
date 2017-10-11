package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりのダメージのインターフェース */
interface Damage {
    /**
     * ダメージ量. 0以上の値を取る.
     */
    var damageParam: Float

    enum class Grade {
        NONE, LARGE
    }

    val damageGrade: Grade

    val noDamageTime: Float

    /**
     * ダメージに関する更新処理. ダメージを与える処理は個別の要因に任せるのでここでは行わない.
     */
    fun update()
}
