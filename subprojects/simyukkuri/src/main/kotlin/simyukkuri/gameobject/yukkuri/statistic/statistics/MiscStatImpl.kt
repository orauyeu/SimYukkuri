package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.actions.NoAction
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats

/** [MiscStat]の標準的ゆっくりへの実装 */
class MiscStatImpl(override val isIdiot: Boolean) : MiscStat {
    lateinit var self: YukkuriStats

    override var action: Action = NoAction

    override var isGrabbed: Boolean = false

    override fun isNearTo(other: YukkuriStats): Boolean {
        return self.distance(other) <= self.nearThreshold
    }

    override var hasWrapper = false

    override var hasOkazari = true

    override var isDirty = false

    override var dirtyTime = 0f

    override val isImmoral = false

    override fun willFurifuri(): Boolean {
        if (self.isImmoral)
            if (Math.random() < 0.5)
                return true
        return false
    }

    override fun canSee(other: YukkuriStats): Boolean {
        return self.distance(other) <= self.eyesight
    }

    override var isDead = false

    override fun die() {
        isDead = true
    }

    override var isCrushed = false

    override var isRemoved = false

    override fun remove() {
        TODO("メモリリークする")
        self.partner?.partner = null
        isRemoved = true
    }

    override fun update() {
        dirtyTime += Time.UNIT
        /*
        if (isDead) {
            if (!self.silent) {
                showDead()
            }
        }
        if (self.z > 0.0) {
            showFlying()
        }
        */
    }
}