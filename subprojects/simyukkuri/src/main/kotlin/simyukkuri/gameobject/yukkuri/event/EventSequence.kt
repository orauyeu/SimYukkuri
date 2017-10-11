package simyukkuri.gameobject.yukkuri.event

// TODO: execute前に処理ができるようにする.
// 再帰的なことをしたいときはChainActionと同様なクラスのイベント版を作る.
abstract class EventSequence(internal val events: Array<out IndividualEvent>) : IndividualEvent {
    private var currentEventIndex = 0
    private var currentEvent: IndividualEvent = events[0]

    override final var hasEnded = false
        private set
    override final val currentAction
        get() = currentEvent.currentAction

    override final fun execute() {
        currentEvent.execute()
        if (!currentEvent.hasEnded)
            return

        currentEventIndex++
        if (currentEventIndex >= events.size)
            hasEnded = true
        else
            currentEvent = events[currentEventIndex]
    }

    final override fun interrupt() {
        currentEvent.interrupt()
    }
}

/** 指定されたイベントから[EventSequence]を作成する. */
fun eventSequenceOf(vararg events: IndividualEvent): EventSequence {
    return object : EventSequence(events) {
        override fun isTheSameAs(other: IndividualEvent): Boolean {
            if (other !is EventSequence) return false
            if (other.events.size != events.size) return false
            for (i in events.indices) if (events[i] != other.events[i])
                return false
            return true
        }
    }
}