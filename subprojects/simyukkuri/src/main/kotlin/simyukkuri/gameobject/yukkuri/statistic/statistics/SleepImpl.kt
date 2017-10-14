package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** [Sleep]の標準的ゆっくりへの実装 */
class SleepImpl : Sleep {
    lateinit var self: YukkuriStats

    override var sleepiness = 0f
        set(value) {
            field = if (value < 0f) 0f else value
        }

    override val isSleepy: Boolean
        get() = sleepiness > self.sleepyThreshold

    // TODO: 起きてすぐは眠れないようにする.
    // TODO: 睡眠不足のときの処理
    override fun update() {
        sleepiness += Time.UNIT
    }
}