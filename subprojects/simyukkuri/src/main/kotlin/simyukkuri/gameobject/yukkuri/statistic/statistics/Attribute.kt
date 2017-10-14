package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりの変化しないパラメータのインターフェース */
interface Attribute {
    val damageThreshold: Int
    val happySec: Int
    val angrySec: Int
    val scaredSec: Int
    val fullnessThresholds: List<Int>
    val maxFullness: Int
    val damagePerHunger: Int
    val nearThreshold: Int
    val eyesight: Int
    val speed: Int
    val jumpHeight: Int
    val unitPoo: Int
    val gestationSec: Int
    val bearingSec: Int
    val sleepyThreshold: Int
    val sexuallyExcitedSec: Int
    val fullnessConsumedBySukkiring: Int
    val fullnessConsumedByGettingSukkiried: Int
    val probabilityOfGettingSexuallyExcited: Float
    val probabilityOfGettingSexuallyExcitedOfRaper: Float
    val incubationSec: Float
    val probabilityOfInfectionByInsanitationPerSec: Float
    val probabilityOfInfectionByContact: Float
    val decreasingDamageByPeroperoedPerSec: Int
}