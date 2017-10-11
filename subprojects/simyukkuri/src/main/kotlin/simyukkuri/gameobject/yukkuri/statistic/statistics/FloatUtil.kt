package simyukkuri.gameobject.yukkuri.statistic.statistics

fun Float.limited(min: Float, max: Float): Float {
    return when {
        this <= min -> min
        this >= max -> max
        else -> this
    }
}
