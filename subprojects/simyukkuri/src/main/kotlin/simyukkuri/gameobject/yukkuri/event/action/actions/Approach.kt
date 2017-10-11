package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.geometry.HasPosition3
import simyukkuri.geometry.MutableVectorXZ

/** 近づくアクション */
class Approach(val self: YukkuriStat, val dst: HasPosition3) : Action {
    override var hasEnded: Boolean = false

    override val currentAction: Action = this

    override fun execute() {
        val maxMovement = self.speed * Time.UNIT
        if (self.distance2(dst) <= maxMovement + self.radius) {
            val moveVec = MutableVectorXZ(dst.x - self.x, dst.z - self.z)
                .normalize()
                .multiply(self.distance2(dst) - self.radius)
            self.x += moveVec.x
            self.z += moveVec.z
            hasEnded = true
            return
        } else {
            val moveVec = MutableVectorXZ(dst.x - self.x, dst.z - self.z)
                .normalize()
                .multiply(maxMovement)
            self.x += moveVec.x
            self.z += moveVec.z
            return
        }
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is Move) return false
        return dst == other.dst
    }

    override val posture: Posture
        get() = postureByPosition(self, dst)
}