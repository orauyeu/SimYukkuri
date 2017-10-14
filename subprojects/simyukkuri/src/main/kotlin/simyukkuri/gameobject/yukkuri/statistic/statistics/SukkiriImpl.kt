package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.factories.YukkuriFactory
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** [Sukkiri]の標準的ゆっくりへの実装 */
class SukkiriImpl : Sukkiri {
    lateinit var self: YukkuriStats

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

    /** 興奮状態になることができる場合確率で興奮状態になる. */
    fun getHornyRandomly() {
        if (Math.random() < probabilityOfGetHorny) {
            if (self.isAdult && !self.isPregnant || isRaper) {
                isHorny = true
                hornyTime = 0f
                self.says(self.msgList.sexuallyExcited)
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