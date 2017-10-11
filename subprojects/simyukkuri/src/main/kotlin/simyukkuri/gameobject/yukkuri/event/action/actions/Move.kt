package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.geometry.HasPosition3
import simyukkuri.geometry.MutableVectorXZ

/** 指定された位置ぴったりまで移動するアクション. */
class Move(val self: YukkuriStat, val dst: HasPosition3) : SingleAction() {
    override fun execute() {
        val max_movement = self.speed * Time.UNIT
        if (self.distance2(dst) <= max_movement) {
            // 重なった瞬間身体の向きが変わらないように
            val posture = posture
            self.x = dst.x
            self.z = dst.z
            hasEnded = true
        } else {
            val moveVec = MutableVectorXZ(dst.x - self.x, dst.z - self.z)
                .normalize()
                .multiply(max_movement)
            self.x += moveVec.x
            self.z += moveVec.z
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