package simyukkuri.gameobject.yukkuri.factories

import simyukkuri.gameobject.yukkuri.Yukkuri

/** ゆっくりを生成するクラスの雛形 */
abstract class AbstractYukkuriFactory {
    /** 種族と成長段階を指定してゆっくりを作成する. */
    abstract fun createYukkuri(): Yukkuri

    /** 両親のパラメータを元に子供を作成する. */
    abstract fun createYukkuriFromParents(father: Yukkuri, mother: Yukkuri): Yukkuri

    /** 一段階成長したゆっくりを返す. */
    abstract fun grownUp(ykr: Yukkuri): Yukkuri
}