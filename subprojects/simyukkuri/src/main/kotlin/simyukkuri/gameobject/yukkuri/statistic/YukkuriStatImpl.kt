package simyukkuri.gameobject.yukkuri.statistic

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.NoAction
import simyukkuri.gameobject.yukkuri.statistic.statistics.*
import simyukkuri.resource.Messages

/** [YukkuriStat]の実装 */
class YukkuriStatImpl(
    damage: Damage,
    emotion: Emotion,
    family: Family,
    fullness: Fullness,
    growth: Growth,
    message: Message,
    miscStat: MiscStat,
    movement: Movement,
    poo: Poo,
    pregnancy: Pregnancy,
    sleep: Sleep,
    sukkiri: Sukkiri,
    yukabi: Yukabi,
    override val messages: Messages) :
        YukkuriStat,
        Damage by damage,
        Emotion by emotion,
        Family by family,
        Fullness by fullness,
        Growth by growth,
        Message by message,
        MiscStat by miscStat,
        Movement by movement,
        Poo by poo,
        Pregnancy by pregnancy,
        Sleep by sleep,
        Sukkiri by sukkiri,
        Yukabi by yukabi {

    override var event: IndividualEvent = NoAction

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}