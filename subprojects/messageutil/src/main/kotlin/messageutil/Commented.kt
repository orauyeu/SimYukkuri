package messageutil

/** 読み取り専用なコメントを加えてラップするクラス. */
open class Commented<out T>(open val comment: String, open val body: T) {
    operator fun component1() = comment
    operator fun component2() = body
}

/** [Commented]の可変版. */
class MutableCommented<T>(override var comment: String, override var body: T) : Commented<T>(comment, body) {
    constructor(body: T) : this("", body)
    constructor(commented: Commented<T>) : this(commented.comment, commented.body)

    /**
     * コメントに改行してから追記する.
     *
     * 改行は不必要な場合は行われない.
     */
    fun addCommentLine(line: String) {
        if (line.isEmpty()) return

        if (comment.isEmpty())
            comment = line
        else
            comment += "\n" + line
    }

    override fun toString(): String = "Commented($comment, $body)"
}