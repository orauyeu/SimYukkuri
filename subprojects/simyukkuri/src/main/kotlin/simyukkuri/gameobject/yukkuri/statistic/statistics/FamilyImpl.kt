package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** [Family]の実装 */
class FamilyImpl(override val father: YukkuriStat?, override val mother: YukkuriStat?) : Family {
    lateinit var self: YukkuriStat

    override val parents: Set<YukkuriStat>

    init {
        val parents = mutableSetOf<YukkuriStat>()
        if (father != null)
            parents.add(father)
        if (mother != null)
            parents.add(mother)
        this.parents = parents
    }

    override val parent: YukkuriStat?
        get() = mother ?: father ?: null

    override fun isChildOf(other: YukkuriStat): Boolean = other in parents

    override var mate: YukkuriStat? = null

    override fun isMateOf(other: YukkuriStat): Boolean = mate == other

    override val elderSisters: MutableCollection<YukkuriStat> = mutableSetOf()

    override val youngerSisters: MutableCollection<YukkuriStat> = mutableSetOf()

    override val sisters: Collection<YukkuriStat>
        get() = HashSet<YukkuriStat>(elderSisters.size + youngerSisters.size).apply {
            addAll(elderSisters)
            addAll(youngerSisters)
        }

    override fun isElderSisterOf(other: YukkuriStat): Boolean = other in elderSisters

    override fun isYoungerSisterOf(other: YukkuriStat): Boolean = other in youngerSisters

    override fun isSisterOf(other: YukkuriStat): Boolean = isElderSisterOf(other) || isYoungerSisterOf(other)

    override val children: MutableCollection<YukkuriStat> = mutableSetOf()

    override fun isMotherOf(other: YukkuriStat): Boolean = self == other.mother

    override fun isFatherOf(other: YukkuriStat): Boolean = self == other.father

    override fun isParentOf(other: YukkuriStat): Boolean = other in children

    override fun isRelatedToByBlood(other: YukkuriStat): Boolean {
        return isChildOf(other) || isParentOf(other) || isSisterOf(other)
    }

    override fun areFamily(other: YukkuriStat): Boolean = isRelatedToByBlood(other) || isMateOf(other)
}