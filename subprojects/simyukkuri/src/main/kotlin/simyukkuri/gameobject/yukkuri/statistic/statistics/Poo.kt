package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりのうんうんのインターフェース */
interface Poo {
    /**
     * 溜まっているうんうんの量.
     */
    var pooParam: Float

    /** 一回あたりのうんうん量. */
    val unitPoo: Float

    /** うんうんをしたいか */
    val wantToPoo: Boolean

    fun update()
}