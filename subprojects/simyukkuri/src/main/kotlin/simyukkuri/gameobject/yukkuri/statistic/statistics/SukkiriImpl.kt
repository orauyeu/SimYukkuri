package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.factories.YukkuriFactory
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** [Sukkiri]の標準的ゆっくりへの実装 */
class SukkiriImpl : Sukkiri {
    lateinit var self: YukkuriStat

    override var isHorny = false
    override var isRaper = false
    override var isSuperRaper = false
    override var isSukkiring = false

    /** 興奮状態が持続する秒数 */
    private val maxHornyTime = 60f

    /** 興奮状態になってから経過した秒数 */
    var hornyTime = 0f

    /** [getHornyRandomly]で[isHorny]がtrueになる確率 */
    private val probabilityOfGetHorny
        get() = when {
            isSuperRaper -> 1f / 2f
            isRaper -> 1f / 6f
            else -> 1f / 12f
        }

    override val canTryToSukkiri: Boolean
        get() = self.noHungerTime > 10f
                && self.noDamageTime > 10f
                && self.happiness >= Emotion.Happiness.NORMAL
                && !self.wantToPoo
                && (self.isAdult || isRaper)
                && !self.isPregnant
                && !isHorny
                && !self.isScared
                && !self.isAngry

    /** すっきりをすることによって消費する満腹度 */
    override val fullnessConsumedBySukkiring = 10f

    override val fullnessConsumedByBeingSukkiried
        get() = fullnessConsumedBySukkiring * 2f

    fun sukkiri(other: YukkuriStat) {
        self.says(self.messages.sukkiri)
        isSukkiring = true
        isHorny = false
        self.feels(Emotion.Happiness.HAPPY)
        self.fullnessParam -= fullnessConsumedBySukkiring

        // TODO: すっきりが成功しなかったことへの反応
        if (self.hasWrapper) {
            self.isDirty = true
            return
        }
        if (other.hasWrapper) {
            other.isDirty = true
            return
        }

        // すっきりがきちんと行われた場合
        self.contact(other)
        other.isSukkiring = true
        other.isHorny = false
        other.feels(Emotion.Happiness.HAPPY)
        other.fullnessParam -= fullnessConsumedByBeingSukkiried
        // TODO: レイパーの場合や既に番がいる場合などを考慮する.
        self.mate = other
        other.mate = self
        // TODO: 一度に複数の赤ゆを妊娠できるようにする.
        other.babiesInWomb.add(YukkuriFactory.createYukkuriFromParents(self, other))
    }

    /** 興奮状態になることができる場合確率で興奮状態になる. */
    fun getHornyRandomly() {
        if (Math.random() < probabilityOfGetHorny) {
            if (self.isAdult && !self.isPregnant || isRaper) {
                isHorny = true
                hornyTime = 0f
                self.says(self.messages.excite)
            }
        }
    }

    override fun update() {
        if (!canTryToSukkiri) return

        if (!isHorny) {
            getHornyRandomly()
        } else {
            hornyTime += Time.UNIT
            if (hornyTime >= maxHornyTime) {
                hornyTime = 0f
                isHorny = false
            }
        }
    }
}