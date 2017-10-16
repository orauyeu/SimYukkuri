package simyukkuri.gameobject.yukkuri.statistic.statistics

import org.apache.commons.lang3.RandomUtils
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

class YukabiImpl : Yukabi {
    lateinit var self: YukkuriStats

    override var isInfectedWithYukabi = false

    /** ゆかびに感染してから経過した時間 */
    var infectionTime = 0f

    override var hasDevelopedYukabi = false

    override fun getInfectedWithYukabiRandomly() {
        if (Math.random() < self.probabilityOfInfectionByContact)
            isInfectedWithYukabi = true
    }

    override fun contact(other: YukkuriStats) {
        if (isInfectedWithYukabi && !other.isInfectedWithYukabi) {
            other.getInfectedWithYukabiRandomly()
        } else if (!isInfectedWithYukabi && other.isInfectedWithYukabi) {
            getInfectedWithYukabiRandomly()
        }
    }

    override fun update() {
        if (self.isDirty) {
            if (RandomUtils.nextFloat() < self.probabilityOfInfectionByInsanitationPerSec * Time.UNIT) {
                isInfectedWithYukabi = true
            }
        }
        if (isInfectedWithYukabi) {
            infectionTime += Time.UNIT
            if (infectionTime > self.incubationSec) {
                hasDevelopedYukabi = true
            }
        }
        if (hasDevelopedYukabi) {
            self.damageParam += Time.UNIT
        }
    }
}
