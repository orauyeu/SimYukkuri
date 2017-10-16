package simyukkuri.gameobject.yukkuri.factory

import simyukkuri.gameobject.yukkuri.Yukkuri

/** ゆっくりを生成するクラスのインターフェース */
interface YukkuriFactory {
    /** 種族と成長段階を指定してゆっくりを作成する. */
    abstract fun createYukkuri(): Yukkuri
}