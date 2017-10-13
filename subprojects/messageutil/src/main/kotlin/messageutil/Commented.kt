package messageutil

/** 読み取り専用なコメントを加えてラップするクラス. */
open class Commented<out T>(open val commentLines: List<String>, open val body: T) {
    operator fun component1() = commentLines
    operator fun component2() = body
}

fun <T> Commented<T>.toMutableCommented(): MutableCommented<T> = MutableCommented(this)

/** 可変なコメントを加えてラップするクラス. */
class MutableCommented<T>(override var commentLines: MutableList<String>, override var body: T) : Commented<T>(commentLines, body) {
    constructor(body: T) : this(mutableListOf(""), body)
    constructor(commented: Commented<T>) : this(commented.commentLines.toMutableList(), commented.body)

    override fun toString(): String = "Commented($commentLines, $body)"
}