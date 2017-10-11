package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time


/**
 * [Emotion]の標準的ゆっくりへの実装.
 */
class EmotionImpl : Emotion {
    override var happiness = Emotion.Happiness.NORMAL

    /** 現在の幸福感情を持ってから経過した時間 */
    var happinessTime = 0f

    /** 幸福感情を維持する時間 */
    val happinessPeriod = 60f

    /**
     * 幸福をセットする. 他の感情にも影響を与える.
     *
     * 幸せになったとき[isScared]と[isAngry]がfalseになる.
     * 悲しみ状態になったとき[isAngry]がfalseになる.
     */
    override fun feels(happiness: Emotion.Happiness) {
        happinessTime = 0f
        when (happiness) {
        // 弱い感情で強い感情をかき消さないようにする
            Emotion.Happiness.SAD -> {
                if (this.happiness != Emotion.Happiness.VERY_SAD) {
                    this.happiness = happiness
                }
            }
            Emotion.Happiness.HAPPY -> {
                if (this.happiness != Emotion.Happiness.VERY_HAPPY) {
                    this.happiness = happiness
                }
            }
            else -> this.happiness = happiness
        }

        // 他の感情に影響を与える.
        if (this.happiness >= Emotion.Happiness.HAPPY) {
            isScared = false
            isAngry = false
        } else if (this.happiness <= Emotion.Happiness.SAD) {
            isAngry = false
        }
    }

    // メモリ節約したい場合はcompanionの値をゲットするようにする
    /** 怒りが収まるのにかかる時間 */
    protected val angryPeriod = 60f

    /** 怒ってから経過した時間 */
    private var angryTime = 0f

    override var isAngry = false

    override fun getAngry() {
        isAngry = true
        angryTime = 0f
    }

    /** 怒りが収まるのにかかる時間 */
    protected val scaredPeriod = 60f

    /** 怒ってから経過した時間 */
    private var scaredTime = 0f

    override var isScared = false

    override fun getScared() {
        isScared = true
        scaredTime = 0f
    }

    override fun resetEmotion() {
        happiness = Emotion.Happiness.NORMAL
        isAngry = false
        isScared = false
    }

    /** 感情の更新処理. */
    override fun update() {
        if (happiness != Emotion.Happiness.NORMAL) {
            happinessTime -= Time.UNIT
            if (happinessTime >= happinessPeriod) {
                happiness = Emotion.Happiness.NORMAL
            }
        }
        if (isAngry) {
            angryTime -= Time.UNIT
            if (angryTime >= angryPeriod) {
                isAngry = false
            }
        }
        if (isScared) {
            scaredTime -= Time.UNIT
            if (scaredTime >= scaredPeriod) {
                isScared = false
            }
        }
        /* TODO
        if (isRelaxedLately) {
            excitingPeriod = 0
            if (willDoFurifri()) {
                setMessage(getMessage("FuriFuri"), 30)
                isFurifuring = true
                stay(30)
            } // ゲスのときは確実に, そうでないときは確率でリラックス時のメッセージ
            else if (isRude || random.nextInt(messageDiscipline + 2) == 0) {
                setMessage(getMessage("Relax"), 30)
                stay(30)
            } else if (!isRude || !isAdult) {
                setMessage(getMessage("NobiNobi"), 36)
                isDoingNobinobi = true
                stay(45)
            }
        }
        */
    }
}
