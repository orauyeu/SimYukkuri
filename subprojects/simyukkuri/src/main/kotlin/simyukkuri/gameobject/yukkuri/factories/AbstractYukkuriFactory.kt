package simyukkuri.gameobject.yukkuri.factories

import messageutil.MessageCollection
import simyukkuri.GameScene
import simyukkuri.gameobject.yukkuri.Yukkuri
import simyukkuri.gameobject.yukkuri.YukkuriImpl
import simyukkuri.gameobject.yukkuri.ai.Ai
import simyukkuri.gameobject.yukkuri.ai.EventManager
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStatsImpl
import simyukkuri.gameobject.yukkuri.statistic.statistics.*
import simyukkuri.resource.ImageList


/** 種々のパラメータを指定してゆっくりを作成する. */
fun createYukkuri(scene: GameScene, imageList: ImageList, x: Double, y: Double, z: Double, growthStage: Growth.GrowthStage, messageCollection: MessageCollection, isIdiot: Boolean, unitPoo: Float, gestationPeriod: Int): Yukkuri {
    val damage = DamageImpl()
    val emotion = EmotionImpl()
    val family = FamilyImpl(null, null)
    val fullness = FullnessImpl()
    val growth = GrowthImpl(growthStage)
    val message = MessageImpl()
    val messagePicker = MessagePicker(messageCollection)
    val miscStat = MiscStatImpl(isIdiot)
    val movement = MovementImpl(x, y, z)
    val poo = PooImpl(unitPoo)
    val pregnancy = PregnancyImpl(gestationPeriod)
    val sleep = SleepImpl()
    val sukkiri = SukkiriImpl()
    val yukabi = YukabiImpl()
    val stats = YukkuriStatsImpl(
            damage,
            emotion,
            family,
            fullness,
            growth,
            message,
            messagePicker,
            miscStat,
            movement,
            poo,
            pregnancy,
            sleep,
            sukkiri,
            yukabi
    )
    damage.self = stats
    family.self = stats
    fullness.self = stats
    messagePicker.self = stats
    miscStat.self = stats
    movement.self = stats
    poo.self = stats
    pregnancy.self = stats
    sukkiri.self = stats
    yukabi.self = stats
    return YukkuriImpl(stats, Ai(EventManager(stats), scene), imageList)
}

/* TODO
/** 両親のパラメータを元に子供を作成する. */
abstract fun createYukkuriFromParents(father: Yukkuri, mother: Yukkuri): Yukkuri

/** 一段階成長したゆっくりを返す. */
abstract fun grownUp(ykr: Yukkuri): Yukkuri
*/
