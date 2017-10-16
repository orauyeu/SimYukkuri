package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりのうんうんのインターフェース */
interface Poo {
    /**
     * 溜まっているうんうんの量.
     */
    var pooParam: Float

    /** うんうんをしたいか */
    val wantToPoo: Boolean

    fun update()
}