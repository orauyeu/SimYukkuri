package simyukkuri.gameobject.yukkuri.ai

import simyukkuri.GameScene
import simyukkuri.gameobject.yukkuri.event.action.actions.*
import simyukkuri.gameobject.yukkuri.event.individualevents.TakeCare
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion

/** ゆっくりの現在のtickでの行動を決定するクラス */
class Ai(val eventManager: EventManager, val gameScene: GameScene) {
    val self = eventManager.self
    // もし一々インスタンスを作るのがパフォーマンス的に問題であればenumで一度全てのアクションを定めてそれを渡す.
    fun ai() {
        if (self.action is FlyAway) return

        if (self.action is Sleep) {
            when {
                self.isInTravail ->
                    eventManager.execute(Bear(self, gameScene))

                self.wantToPoo -> {
                    if (self.isBaby)
                        TODO("うんうんを漏らす")
                    else
                        eventManager.execute(Poop(self))
                }
                else ->
                    eventManager.proceed()
            }
            return
        }

        when {
            gameScene.yukkuris.any { it.isRaper && self.canSee(it) } -> {
                val raper = gameScene.yukkuriNearestTo(self) { it.isRaper }!!
                eventManager.execute(RunAway(self, raper))
            }
            self.isInTravail ->
                eventManager.execute(Bear(self, gameScene))

            self.wantToPoo ->
                eventManager.execute(Poop(self))

            self.isHorny -> {
                val target = findSukkiriTarget()
                if (target == null)
                    eventManager.execute(Search(self))
                else
                    eventManager.execute(Sukkiri(self, target))
            }
            !self.isHorny && !self.isScared && self.happiness != Emotion.Happiness.VERY_SAD -> {
                eventManager.execute(Sleep(self))
            }
            willMoveToBePeroperoed() ->
                eventManager.execute(Approach(self, self.parent!!))

            willTakeCareOfChild() ->
                eventManager.execute(TakeCare(self, nearest(self.children, self) { willTakeCareOf(it) }!!))

            !self.event.hasEnded ->
                eventManager.proceed()

            else ->
                killsTime()
        }
    }

    /**
     * すっきりの対象を返す.
     *
     * 対象が存在しない場合はnullを返す.
     */
    fun findSukkiriTarget(): YukkuriStat? {
        if (self.mate != null) {
            return self.mate
        } else {
            return gameScene.yukkuriNearestTo(self)
        }
    }

    /** 親にぺろぺろされようとするかを返す. */
    fun willMoveToBePeroperoed(): Boolean {
        if (self.isDirty && !self.isAdult)
            if (!self.parents.isEmpty())
                return true
        return false
    }

    /** 対象を世話しようとするかを返す. */
    fun willTakeCareOf(other: YukkuriStat): Boolean {
        if (!other.isDirty)
            return false
        if (self.isParentOf(other)) {
            if (self.isMotherOf(other))
                if (self.isAdult)
                    return true
            if (self.isAdult && !other.isAdult)
                return true
        }

        return false
    }

    /** 子ゆを世話しようとするかを返す. */
    fun willTakeCareOfChild(): Boolean {
        return self.isAdult && self.children.any { it.isDirty && (it.isAdult || self.isMotherOf(it)) }
    }

    /** 暇つぶしをする. */
    fun killsTime() {
        TODO("not implemented")
        // おかざりのないゆっくりを制裁する
        // すっきりする
        // 家族のところに行く
    }
}