package shitarabato3

import messageutil.Growth
import messageutil.Love
import messageutil.Type

/** 指定された属性が含まれるかを返す. */
internal fun Type.contains(attr: String): Boolean {
    when (attr) {
        "baby" -> return growth == Growth.BABY
        "child" -> return growth == Growth.CHILD
        "adult" -> return growth == Growth.ADULT
    }

    if (attr == "rude")
        return isImmoral

    if (attr == "normal")
        return !isImmoral

    if (attr == "damage")
        return isDamaged

    if (attr == "pooSlave")
        return isUnunSlave

    when (attr) {
        "dislikeplayer" -> return love == Love.HATE
        "loveplayer" -> return love == Love.LOVE
    }

    if (attr == "pants")
        return hasOkurumi

    throw Exception("不正な属性名です: $attr")
}

/** 属性を加える. */
internal fun Type.added(attr: String): Type {
    when (attr) {
        "baby" ->
            return this.copy(growth = Growth.BABY)
        "child" ->
            return this.copy(growth = Growth.CHILD)
        "adult" ->
            return this.copy(growth = Growth.ADULT)
    }

    when (attr) {
        "rude" -> return this.copy(isImmoral = true)
        "normal" -> return this.copy(isImmoral = false)
    }
    if (attr == "rude")
        return this.copy(isImmoral = true)

    if (attr == "damage")
        return this.copy(isDamaged = true)

    if (attr == "pooSlave")
        return this.copy(isUnunSlave = true)

    when (attr) {
        "dislikeplayer" ->
            return this.copy(love = Love.HATE)
        "loveplayer" ->
            return this.copy(love = Love.LOVE)
    }

    if (attr == "pants")
        return this.copy(hasOkurumi = true)

    throw Exception("不正な属性名です: $attr")
}

/** 属性を取り除く. */
internal fun Type.removed(attr: String): Type {
    when (attr) {
        "baby" ->
            return this.copy(growth = Growth.ALL)
        "child" ->
            return this.copy(growth = Growth.ALL)
        "adult" ->
            return this.copy(growth = Growth.ALL)
    }

    if (attr == "rude")
        return this.copy(isImmoral = false)

    if (attr == "damage")
        return this.copy(isDamaged = false)

    if (attr == "pooSlave")
        return this.copy(isUnunSlave = false)

    when (attr) {
        "dislikeplayer" ->
            return this.copy(love = Love.ALL)
        "loveplayer" ->
            return this.copy(love = Love.ALL)
    }

    if (attr == "pants")
        return this.copy(hasOkurumi = false)

    if (attr == "normal")
        return this
    else
        throw Exception("不正な属性名です: $attr")
}