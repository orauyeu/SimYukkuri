package simyukkuri.gameobject.yukkuri.statistic

import messageutil.Condition
import messageutil.Love
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.event.action.actions.NoAction
import simyukkuri.gameobject.yukkuri.statistic.statistics.*

/** [YukkuriStats]の実装 */
class YukkuriStatsImpl(
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
    val msgList: MessagePicker) :
        YukkuriStats,
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

    /** メッセージ用のゆっくりの状態 */
    override val messageCondition: Condition
        get() {
            val growth = when (growthStage) {
                Growth.GrowthStage.BABY -> messageutil.Growth.BABY
                Growth.GrowthStage.CHILD -> messageutil.Growth.CHILD
                Growth.GrowthStage.ADULT -> messageutil.Growth.ADULT
            }
            return Condition(
                    growth = growth,
                    isImmoral = isImmoral,
                    isDamaged = damageGrade >= Damage.Grade.LARGE,
                    isPooSlave = false,
                    love = Love.ALL,
                    hasWrapper = hasWrapper
            )
        }

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}