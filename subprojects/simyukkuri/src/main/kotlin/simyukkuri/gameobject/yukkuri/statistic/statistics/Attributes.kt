package simyukkuri.gameobject.yukkuri.statistic.statistics

import Root
import messageutil.myYaml
import java.io.InputStream

private class AttributeImpl(
    override val damageThreshold: Int,
    override val happySec: Int,
    override val angrySec: Int,
    override val scaredSec: Int,
    override val fullnessThresholds: List<Int>,
    override val maxFullness: Int,
    override val damagePerHunger: Int,
    override val nearThreshold: Int,
    override val eyesight: Int,
    override val speed: Int,
    override val jumpHeight: Int,
    override val unitPoo: Int,
    override val gestationSec: Int,
    override val bearingSec: Int,
    override val sleepyThreshold: Int,
    override val sexuallyExcitedSec: Int,
    override val fullnessConsumedBySukkiring: Int,
    override val fullnessConsumedByGettingSukkiried: Int,
    override val probabilityOfGettingSexuallyExcited: Float,
    override val probabilityOfGettingSexuallyExcitedOfRaper: Float,
    override val incubationSec: Float,
    override val probabilityOfInfectionByInsanitationPerSec: Float,
    override val probabilityOfInfectionByContact: Float,
    override val decreasingDamageByPeroperoedPerSec: Int
) : Attribute

/** [Attribute]の各プロパティをそれぞれ掛けた[Attribute]を返す. */
fun productOf(vararg attribute: Attribute): Attribute = TODO("not implemented")

/** 指定された名前の[Attribute]を表すYAMLファイルの[InputStream]を返す. */
fun attributeInputStream(name: String): InputStream = Root::class.java.getResourceAsStream("attributes/$name.yml")

val defaultAttribute: Attribute by lazy { myYaml.load(attributeInputStream("default")) as Attribute }

val marisaMagnification: Attribute by lazy { myYaml.load(attributeInputStream("marisa")) as Attribute }

val adultMagnification: Attribute by lazy { myYaml.load(attributeInputStream("adult")) as Attribute }