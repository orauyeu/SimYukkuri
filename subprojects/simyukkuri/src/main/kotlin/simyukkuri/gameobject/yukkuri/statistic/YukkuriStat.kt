package simyukkuri.gameobject.yukkuri.statistic

import simyukkuri.gameobject.yukkuri.event.IndividualEvent
import simyukkuri.gameobject.yukkuri.statistic.statistics.*
import simyukkuri.resource.Messages

/** ゆっくりのステータスのインターフェース */
interface YukkuriStat :
        Damage, Emotion, Family, Fullness, Growth, Message, MiscStat, Movement, Poo, Pregnancy, Sleep, Sukkiri, Yukabi {
    val messages: Messages

    var event: IndividualEvent

    override fun update()
}