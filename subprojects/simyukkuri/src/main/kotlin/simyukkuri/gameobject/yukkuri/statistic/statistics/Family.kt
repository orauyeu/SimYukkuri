package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** ゆっくりの家族のインターフェース */
interface Family {
    /** 番の相手 */
    var mate: YukkuriStat?

    /** 指定されたゆっくりと番であるかを返す. */
    fun isMateOf(other: YukkuriStat): Boolean

    /** 姉妹のコレクション */
    val sisters: Collection<YukkuriStat>

    /** 姉のコレクション */
    val elderSisters: MutableCollection<YukkuriStat>

    /** 妹のコレクション */
    val youngerSisters: MutableCollection<YukkuriStat>

    /** 指定されたの姉妹であるかを返す. */
    fun isSisterOf(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりの姉であるかを返す. */
    fun isElderSisterOf(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりの妹であるかを返す. */
    fun isYoungerSisterOf(other: YukkuriStat): Boolean

    /** 父親 */
    val father: YukkuriStat?

    /** 母親 */
    val mother: YukkuriStat?

    /** 母親を優先して, 親の一方を返す. 両親がどちらもいない場合はnull. */
    val parent: YukkuriStat?

    /** 両親の集合 */
    val parents: Set<YukkuriStat>

    /** このゆっくりが指定されたゆっくりの子供であるかを返す. */
    fun isChildOf(other: YukkuriStat): Boolean

    /** 子供のコレクションを返す. */
    val children: MutableCollection<YukkuriStat>

    /** このゆっくりが指定されたゆっくりの親であるかを返す. */
    fun isParentOf(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりの母親であるかを返す. */
    fun isMotherOf(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりの父親であるかを返す. */
    fun isFatherOf(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりと血縁関係にあるかを返す. */
    fun isRelatedToByBlood(other: YukkuriStat): Boolean

    /** このゆっくりが指定されたゆっくりと家族であるかを返す. */
    fun areFamily(other: YukkuriStat): Boolean
}