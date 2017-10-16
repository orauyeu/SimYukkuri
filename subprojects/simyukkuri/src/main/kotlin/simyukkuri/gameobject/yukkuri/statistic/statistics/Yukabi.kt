package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/**
 * ゆかびのインターフェース.
 *
 * ゆかびは感染した後, 潜伏期間を経て発症する.
 */
interface Yukabi {
    /**
     * ゆかびに感染しているか.
     *
     * 既に発症している場合もtrue.
     */
    var isInfectedWithYukabi: Boolean

    /** ゆかびを発症しているか */
    val hasDevelopedYukabi: Boolean

    /** ある確率でゆかびに感染する. */
    fun getInfectedWithYukabiRandomly()

    /**
     * 接触によるゆかびの感染を判定する.
     *
     * 自分か相手のどちらかがゆかびに感染している場合一定の確率でもう一方に感染する.
     */
    fun contact(other: YukkuriStats)

    fun update()
}
