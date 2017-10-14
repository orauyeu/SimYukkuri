package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** [Family]の実装 */
class FamilyImpl(override val father: YukkuriStats?, override val mother: YukkuriStats?) : Family {
    lateinit var self: YukkuriStats

    override val parents: Set<YukkuriStats>

    init {
        val parents = mutableSetOf<YukkuriStats>()
        if (father != null)
            parents.add(father)
        if (mother != null)
            parents.add(mother)
        this.parents = parents
    }

    override val parent: YukkuriStats?
        get() = mother ?: father

    override fun isChildOf(other: YukkuriStats): Boolean = other in parents

    override var partner: YukkuriStats? = null

    override fun isPartnerOf(other: YukkuriStats): Boolean = partner == other

    override val elderSisters: MutableCollection<YukkuriStats> = mutableSetOf()

    override val youngerSisters: MutableCollection<YukkuriStats> = mutableSetOf()

    override val sisters: Collection<YukkuriStats>
        get() = HashSet<YukkuriStats>(elderSisters.size + youngerSisters.size).apply {
            addAll(elderSisters)
            addAll(youngerSisters)
        }

    override fun isElderSisterOf(other: YukkuriStats): Boolean = other in elderSisters

    override fun isYoungerSisterOf(other: YukkuriStats): Boolean = other in youngerSisters

    override fun isSisterOf(other: YukkuriStats): Boolean = isElderSisterOf(other) || isYoungerSisterOf(other)

    override val children: MutableCollection<YukkuriStats> = mutableSetOf()

    override fun isMotherOf(other: YukkuriStats): Boolean = self == other.mother

    override fun isFatherOf(other: YukkuriStats): Boolean = self == other.father

    override fun isParentOf(other: YukkuriStats): Boolean = other in children

    override fun isRelatedToByBlood(other: YukkuriStats): Boolean {
        return isChildOf(other) || isParentOf(other) || isSisterOf(other)
    }

    override fun areFamily(other: YukkuriStats): Boolean = isRelatedToByBlood(other) || isPartnerOf(other)
}