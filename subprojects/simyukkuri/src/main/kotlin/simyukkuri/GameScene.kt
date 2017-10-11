package simyukkuri

import simyukkuri.gameobject.yukkuri.Yukkuri
import simyukkuri.geometry.HasPosition3

/** ゲームのマップの状態クラス. */
class GameScene(val maxX: Double, val maxY: Double, val maxZ: Double) {
    /** シーン上のゆっくりのコレクション */
    val yukkuris: Collection<Yukkuri>
        get() = internalYukkuris

    /** 書き換え可能なシーン上のゆっくりのコレクション */
    private val internalYukkuris = mutableSetOf<Yukkuri>()

    /**
     * 条件に当てはまるゆっくりの中で指定された位置の最も近くにいるゆっくりを返す. あてはまるゆっくりがいないときnullを返す.
     *
     * ゆっくりが指定されていた場合そのゆっくり自身はリターンの候補にならない.
     *
     * @param condition 条件. trueを返すときそのゆっくりはリターンの候補になる. falseを返すときリターンの候補から除外される.
     */
    fun yukkuriNearestTo(position: HasPosition3, condition: (Yukkuri) -> Boolean = { true }): Yukkuri? {
        var currentMinDistance: Double? = null
        var currentNearestYukkuri: Yukkuri? = null
        for (other in internalYukkuris) {
            if (other === position) continue
            if (!condition(other)) continue

            if (currentMinDistance == null) {
                currentMinDistance = position.distance(other)
                currentNearestYukkuri = other
            } else {
                val dist = position.distance(other)
                if (dist >= currentMinDistance) continue
                currentMinDistance = dist
                currentNearestYukkuri = other
            }
        }
        return currentNearestYukkuri
    }

    /** tickの終わりにフィールドに追加するゆっくりのコレクション */
    private val newcomers = mutableSetOf<Yukkuri>()

    /** このシーンにゆっくりを追加する. */
    fun add(ykr: Yukkuri) {
        newcomers.add(ykr)
    }

    fun update() {
        val iter = internalYukkuris.iterator()
        while (iter.hasNext()) {
            val ykr = iter.next()
            if (ykr.isRemoved)
                iter.remove()
        }
        internalYukkuris.addAll(newcomers)
        newcomers.clear()
    }
}