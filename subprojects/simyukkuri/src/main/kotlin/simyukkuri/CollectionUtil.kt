package simyukkuri

import org.apache.commons.lang3.RandomUtils

/** 要素を一つランダムに返す. */
fun <T> Array<T>.randomElement(): T {
    return this[RandomUtils.nextInt(0, this.size)]
}

/** 要素を一つランダムに返す. */
fun <T> Collection<T>.randomElement(): T {
    return this.elementAt(RandomUtils.nextInt(0, this.size))
}