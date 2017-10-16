package simyukkuri.gameobject.yukkuri.event.individualevents

import simyukkuri.GameScene
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.actions.Approach
import simyukkuri.gameobject.yukkuri.event.action.actions.Search
import simyukkuri.gameobject.yukkuri.event.action.actions.Sukkiri
import simyukkuri.gameobject.yukkuri.event.eventSequenceOf
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

// TODO: 対象のゆっくりが視界から消えたときイベントを中止する.
/** すっきりイベント */
class SukkiriEvent(self: YukkuriStats, gameScene: GameScene) : IndividualEvent {
    override var hasEnded: Boolean = false
    override val currentAction: Action
        get() = currentEvent.currentAction

    var target: YukkuriStats?
    var currentEvent: IndividualEvent

    init {
        if (self.partner != null)
            target = self.partner
        else
            target = gameScene.yukkuriNearestTo(self)

        if (target != null) {
            currentEvent = eventSequenceOf(Approach(self, target!!), Sukkiri(self, target!!))
        } else
            currentEvent = eventSequenceOf(Search(self), Approach(self, target!!), Sukkiri(self, target!!))
    }

    override fun execute() {
        this.currentEvent.execute()
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean = other is SukkiriEvent
}