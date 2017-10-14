package simyukkuri.gameobject.yukkuri.statistic

import messageutil.Condition
import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.statistic.statistics.*

/** ゆっくりのステータスのインターフェース */
interface YukkuriStats :
        Damage, Emotion, Family, Fullness, Growth, Message, MiscStat, Movement, Poo, Pregnancy, Sleep, Sukkiri, Yukabi {
    val msgList: MessagePicker

    val messageCondition: Condition

    var event: IndividualEvent

    override fun update()
}