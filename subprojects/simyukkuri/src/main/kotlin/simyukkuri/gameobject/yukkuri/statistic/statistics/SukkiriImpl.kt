package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** [Sukkiri]の標準的ゆっくりへの実装 */
class SukkiriImpl : Sukkiri {
    lateinit var self: YukkuriStats

    override var isSexuallyExcited = false
    override var isRaper = false
    override var isSukkiring = false

    /** 興奮状態になってから経過した秒数 */
    var elapsedSexuallyExcitedSec = 0f

    /** [getSexuallyExcitedRandomly]で[isSexuallyExcited]がtrueになる確率 */
    private val probabilityOfGetHorny
        get() = when {
            isRaper -> self.probabilityOfGettingSexuallyExcitedOfRaper
            else -> self.probabilityOfGettingSexuallyExcited
        }

    override val canTryToSukkiri: Boolean
        get() = self.noHungerTime > 10f
                && self.noDamageTime > 10f
                && self.happiness >= Emotion.Happiness.NORMAL
                && !self.wantToPoo
                && (self.isAdult || isRaper)
                && !self.isPregnant
                && !isSexuallyExcited
                && !self.isScared
                && !self.isAngry

    /** 興奮状態になることができる場合確率で興奮状態になる. */
    fun getSexuallyExcitedRandomly() {
        if (Math.random() < probabilityOfGetHorny) {
            if (self.isAdult && !self.isPregnant || isRaper) {
                isSexuallyExcited = true
                elapsedSexuallyExcitedSec = 0f
                self.says(self.msgList.sexuallyExcited)
            }
        }
    }

    override fun update() {
        if (!canTryToSukkiri) return

        if (!isSexuallyExcited) {
            getSexuallyExcitedRandomly()
        } else {
            elapsedSexuallyExcitedSec += Time.UNIT
            if (elapsedSexuallyExcitedSec >= self.sexuallyExcitedSec) {
                elapsedSexuallyExcitedSec = 0f
                isSexuallyExcited = false
            }
        }
    }
}