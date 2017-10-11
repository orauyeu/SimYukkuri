package simyukkuri.gameobject.yukkuri.ai

import simyukkuri.geometry.HasPosition3

/**
 * コレクションの中から条件を満たし指定された位置の最も近くにあるものを返す.
 *
 * 指定されたもの自身はリターンの対象から除外される.
 */
fun <T : HasPosition3> nearest(collection: Collection<T>, position: T, condition: (T) -> Boolean): T? {
    var currentMinDistance: Double? = null
    var currentNearestYukkuri: T? = null
    for (other in collection) {
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