package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/**
 * 発言アクション.
 *
 * 他のアクションを遮らずに発言させたい場合は[simyukkuri.gameobject.yukkuri.statistic.statistics.Message]を利用する.
 *
 * @constructor セリフを指定された体勢で数秒表示するアクションを返す.
 */
class Say(private val self: YukkuriStat, private val saying: String, private var period: Float = 2f, override val posture: Posture = Posture.FRONT) : SingleAction() {
    constructor(self: YukkuriStat, target: YukkuriStat, saying: String, period: Float) : this(self, saying, period, postureByPosition(self, target))

    private var initialized = false

    override fun execute() {
        self.says(saying)
        period -= Time.UNIT
        if (period < 0f)
            hasEnded = true
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Say) return false
        return saying == other.saying && posture == other.posture
    }
}