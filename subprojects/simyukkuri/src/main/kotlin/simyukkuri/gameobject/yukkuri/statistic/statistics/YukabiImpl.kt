package simyukkuri.gameobject.yukkuri.statistic.statistics

import org.apache.commons.lang3.RandomUtils
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

class YukabiImpl : Yukabi {
    lateinit var self: YukkuriStats

    override var isInfectedWithYukabi = false

    /** ゆかびに感染してから発症するまでの時間 */
    val incubationPeriod = 180f

    /** ゆかびに感染してから経過した時間 */
    var infectionTime = 0f

    override var hasDevelopedYukabi = false

    /** 汚れているとき1秒間でゆかびに感染する確率 */
    protected val probabilityOfInfection = 1f / 180f

    // TODO: ダメージや種による感染のしやすさを反映する.
    /** 接触によってゆかびが感染する確率 */
    protected val probabilityOfInfectionByContact = 1f / 3f

    override fun getInfectedWithYukabiInProbability() {
        if (Math.random() < probabilityOfInfectionByContact)
            isInfectedWithYukabi = true
    }

    override fun contact(other: YukkuriStats) {
        if (isInfectedWithYukabi && !other.isInfectedWithYukabi) {
            other.getInfectedWithYukabiInProbability()
        } else if (!isInfectedWithYukabi && other.isInfectedWithYukabi) {
            getInfectedWithYukabiInProbability()
        }
    }

    override fun update() {
        if (self.isDirty) {
            if (RandomUtils.nextFloat() < probabilityOfInfection * Time.UNIT) {
                isInfectedWithYukabi = true
            }
        }
        if (isInfectedWithYukabi) {
            infectionTime += Time.UNIT
            if (infectionTime > incubationPeriod) {
                hasDevelopedYukabi = true
            }
        }
        if (hasDevelopedYukabi) {
            self.damageParam += Time.UNIT
        }
    }
}
