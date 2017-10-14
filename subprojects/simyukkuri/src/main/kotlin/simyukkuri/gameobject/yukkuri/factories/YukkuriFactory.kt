package simyukkuri.gameobject.yukkuri.factories

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** ゆっくりを生成するクラスのインターフェース */
interface YukkuriFactory {
    companion object {
        /** 両親からその子供のゆっくりを作成する. */
        fun createYukkuriFromParents(father: YukkuriStats, mother: YukkuriStats): YukkuriStats
    }
}