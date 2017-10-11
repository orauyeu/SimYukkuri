package simyukkuri.gameobject

import simyukkuri.geometry.HasPosition3

/** 位置を持って, 画面に表示されるオブジェクト */
interface GameObject : HasPosition3 {
    /** 1tick分更新する. */
    fun update()
}