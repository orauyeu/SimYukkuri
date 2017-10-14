package simyukkuri.gameobject.yukkuri.statistic

import messageutil.Condition
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.statistic.statistics.*

/** ゆっくりのステータスのインターフェース */
interface YukkuriStat :
        Damage, Emotion, Family, Fullness, Growth, Message, MiscStat, Movement, Poo, Pregnancy, Sleep, Sukkiri, Yukabi {
    /** このゆっくりのメッセージのリスト */
    val msgList: MessagePicker

    var event: IndividualEvent

    val messageCondition: Condition

    override fun update()
}