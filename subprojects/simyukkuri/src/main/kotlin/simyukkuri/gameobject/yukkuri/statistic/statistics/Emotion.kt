package simyukkuri.gameobject.yukkuri.statistic.statistics

// TODO: 感情の排他性をここに移す
/**
 * ゆっくりの感情のインターフェース.
 *
 * 各感情を持った場合一定時間その感情を維持する.
 */
interface Emotion {
    enum class Happiness {
        VERY_SAD, SAD, NORMAL, HAPPY, VERY_HAPPY
    }

    val happiness: Happiness

    /**
     * 幸福をセットする. 他の感情にも影響を与える.
     */
    fun feels(happiness: Happiness)

    /** プレイヤーに対して怒っているか */
    val isAngry: Boolean

    /** 怒り状態になる. */
    fun getAngry()

    /** 怯えているか */
    val isScared: Boolean

    /** 怯え状態になる. */
    fun getScared()

    /** 全ての感情をデフォルトに戻す. */
    fun resetEmotion()

    fun update()
}