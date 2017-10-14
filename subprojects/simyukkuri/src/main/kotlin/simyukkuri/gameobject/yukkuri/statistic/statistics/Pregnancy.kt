package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** ゆっくりの妊娠のインターフェース */
interface Pregnancy {
    /** 妊娠しているか. */
    val isPregnant: Boolean

    /** 産気づいているか. */
    val isInTravail: Boolean

    /** お腹の中にいる子供のコレクション */
    val babiesInWomb: MutableSet<YukkuriStats>

    fun update()
}
