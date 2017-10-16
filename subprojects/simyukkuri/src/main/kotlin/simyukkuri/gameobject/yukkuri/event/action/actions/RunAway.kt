package simyukkuri.gameobject.yukkuri.event.action.actions

import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.Posture
import simyukkuri.gameobject.yukkuri.event.action.SingleAction
import simyukkuri.gameobject.yukkuri.event.action.postureByPosition
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.geometry.MutableVectorXZ

// TODO: 発言を引数にとり, それを言いながら逃げるようにする.
/**
 * 逃げるアクション.
 *
 * @param from 逃げる相手
 */
class RunAway(val self: YukkuriStats, private val from: YukkuriStats) : SingleAction() {
    // TODO: 壁に引っかかったときの処理.
    override fun execute() {
        val maxMovement = self.speed * Time.UNIT
        val moveVec = MutableVectorXZ(from.x - self.x, from.z - self.z)
            .normalize()
            .multiply(maxMovement)
        self.x -= moveVec.x
        self.z -= moveVec.z
        if (!self.canSee(from))
            hasEnded = true
    }

    override fun interrupt() = Unit

    override fun isTheSameAs(other: IndividualEvent): Boolean {
        if (other !is RunAway) return false
        return from == other.from
    }

    override val posture: Posture
        get() = postureByPosition(from, self)
}