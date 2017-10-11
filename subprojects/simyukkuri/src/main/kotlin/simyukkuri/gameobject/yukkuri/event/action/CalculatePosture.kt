package simyukkuri.gameobject.yukkuri.event.action

import simyukkuri.geometry.HasPosition3

internal fun <E> animation(pattern: Array<out E>, duration: Float, currentTime: Float): E {
    return pattern[((currentTime % (pattern.size * duration)) / duration).toInt()]
}

internal fun postureByPosition(from: HasPosition3, to: HasPosition3): Posture {
    val rad = Math.atan2(to.x - from.x, to.z - from.z)
    return when {
        -Math.PI / 4 <= rad && rad < Math.PI / 4 -> Posture.RIGHT
        Math.PI / 4 <= rad && rad < Math.PI * 3 / 4 -> Posture.BACK
        -Math.PI * 3 / 4 < rad && rad < -Math.PI / 4 -> Posture.FRONT
        else -> Posture.LEFT
    }
}

internal fun randomStandingPosture(): Posture {
    val r = Math.random()
    return when {
        0 <= r && r < 1 / 4 -> Posture.RIGHT
        1 / 4 <= r && r < 1 / 2 -> Posture.BACK
        1 / 2 <= r && r < 3 / 4 -> Posture.LEFT
        else -> Posture.FRONT
    }
}