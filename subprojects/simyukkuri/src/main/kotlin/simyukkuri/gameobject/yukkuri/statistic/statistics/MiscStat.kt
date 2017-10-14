package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.gameobject.yukkuri.event.action.Action
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat

/** クラスを作るほどでもないステータスの集まり. */
interface MiscStat {
    var action: Action

    var isDirty: Boolean

    /** 汚れたままで経過した時間 */
    val dirtyTime: Float

    var isGrabbed: Boolean

    var isDead: Boolean

    fun die()

    var isCrushed: Boolean

    /** おくるみを着ているか */
    var hasWrapper: Boolean

    var hasOkazari: Boolean

    val isImmoral: Boolean

    val isIdiot: Boolean

    /** ふりふりをする判定. */
    fun willFurifuri(): Boolean

    fun canSee(other: YukkuriStat): Boolean

    fun isNearTo(other: YukkuriStat): Boolean

    /**
     * ゆっくりがフィールドから除去されているか.
     *
     * trueの場合tickの終わりにフィールドから取り除かれる.
     */
    val isRemoved: Boolean

    /**
     * ゆっくりをフィールドから除去する.
     *
     * forループ中にフィールド上のゆっくりのコレクションを変更しないために,
     * 直ちに除去するのではなく[isRemoved]をtrueにすることでこのtickの終わりに除去される状態にする.<br>
     * ガベージコレクションの対象となるようにこのゆっくりへの参照をnullにする. (mateなど)
     */
    fun remove()

    fun update()
}