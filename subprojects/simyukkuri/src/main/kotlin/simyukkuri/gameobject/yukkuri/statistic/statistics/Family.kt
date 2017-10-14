package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** ゆっくりの家族のインターフェース */
interface Family {
    /** 番の相手 */
    var partner: YukkuriStats?

    /** 指定されたゆっくりと番であるかを返す. */
    fun isPartnerOf(other: YukkuriStats): Boolean

    /** 姉妹のコレクション */
    val sisters: Collection<YukkuriStats>

    /** 姉のコレクション */
    val elderSisters: MutableCollection<YukkuriStats>

    /** 妹のコレクション */
    val youngerSisters: MutableCollection<YukkuriStats>

    /** 指定されたの姉妹であるかを返す. */
    fun isSisterOf(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりの姉であるかを返す. */
    fun isElderSisterOf(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりの妹であるかを返す. */
    fun isYoungerSisterOf(other: YukkuriStats): Boolean

    /** 父親 */
    val father: YukkuriStats?

    /** 母親 */
    val mother: YukkuriStats?

    /** 母親を優先して, 親の一方を返す. 両親がどちらもいない場合はnull. */
    val parent: YukkuriStats?

    /** 両親の集合 */
    val parents: Set<YukkuriStats>

    /** このゆっくりが指定されたゆっくりの子供であるかを返す. */
    fun isChildOf(other: YukkuriStats): Boolean

    /** 子供のコレクションを返す. */
    val children: MutableCollection<YukkuriStats>

    /** このゆっくりが指定されたゆっくりの親であるかを返す. */
    fun isParentOf(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりの母親であるかを返す. */
    fun isMotherOf(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりの父親であるかを返す. */
    fun isFatherOf(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりと血縁関係にあるかを返す. */
    fun isRelatedToByBlood(other: YukkuriStats): Boolean

    /** このゆっくりが指定されたゆっくりと家族であるかを返す. */
    fun areFamily(other: YukkuriStats): Boolean
}