package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.GameScene
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.MultipleAction
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

/**
 * 出産アクション.
 *
 * もしゲスならば, 出産後ふりふりする.
 */
class Bear(self: YukkuriStat, gameScene: GameScene) : MultipleAction() {
    override var currentAction: Action = BearImpl(self, gameScene)
}

private class BearImpl(val self: YukkuriStat, val gameScene: GameScene) : Action {
    override var hasEnded = false

    override var currentAction: Action = this
        private set

    override val posture: Posture
        get() = Posture.SHOW_ANUS

    private var elapsedTime = 0f
    private val bearingTime = 5f
    // TODO: お腹の中の赤ちゃんが多すぎる場合は失敗する.
    override fun execute() {
        elapsedTime += Time.UNIT
        if (elapsedTime <= bearingTime) return

        if (self.hasWrapper) {
            bearWithWrapper()
            currentAction = Say(self, self.messages.abort)
            return
        } else {
            bearChild()
            when {
                self.isPregnant -> return
                self.willFurifuri() -> {
                    hasEnded = true
                    currentAction = Furifuri()
                    return
                }
                else -> {
                    hasEnded = true
                    return
                }
            }
        }
    }

    /** 子供を一匹産む. */
    private fun bearChild() {
        TODO()
    }

    /** おくるみを着た状態で出産する. */
    private fun bearWithWrapper() {
        self.isDirty = true
        self.babiesInWomb.clear()
        self.feels(Emotion.Happiness.VERY_SAD)
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is Bear || other is BearImpl
}