package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.geometry.Cylindrical

/** ゆっくりの移動, 衝突, 大きさのインターフェース */
interface Movement : Cylindrical {
    val speed: Double

    override var x: Double
    override var y: Double
    override var z: Double

    fun update()
}