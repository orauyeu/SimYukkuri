package simyukkuri.gameobject.yukkuri.factories

import simyukkuri.gameobject.yukkuri.Yukkuri
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** ゆっくりを生成するクラスのインターフェース */
interface YukkuriFactory {
    /** 種族と成長段階を指定してゆっくりを作成する. */
    abstract fun createYukkuri(): Yukkuri
}