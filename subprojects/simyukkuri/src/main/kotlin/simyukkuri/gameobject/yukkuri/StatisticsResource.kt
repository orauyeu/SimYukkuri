package simyukkuri.gameobject.yukkuri

// TODO: 外部ファイルに移す.
abstract class StatisticsResource {
    // tunable parameters for each YukkuriStat
    private val HUNGRYLIMIT = intArrayOf(100 * 24, 100 * 24 * 2, 100 * 24 * 4)
    private val SHITLIMIT = intArrayOf(100 * 12, 100 * 24, 100 * 24)
    private val DAMAGELIMIT = intArrayOf(100 * 24, 100 * 24 * 3, 100 * 24 * 7)
    private var BABYLIMIT = 100 * 24 * 7
    private var CHILDLIMIT = 100 * 24 * 21
    private var LIFELIMIT = 100 * 24 * 365
    private val STEP = intArrayOf(1, 2, 4)        // This should be less than or equal to MAXSTEP.
    private var RELAXPERIOD = 100 * 1
    private var EXCITEPERIOD = 100 * 3
    private var PREGPERIOD = 100 * 24
    private var SLEEPPERIOD = 100 * 3
    private var ACTIVEPERIOD = 100 * 6
    private val ANGRYPERIOD = 100 * 1
    private val SCAREPERIOD = 100 * 1
    private var SAMEDEST = 30
    private var DECLINEPERIOD = 100 * 6 * 10 // 10 min.
    private var DISCIPLINELIMIT = 10
    private val BLOCKEDLIMIT = 60
    private var DIRTYPERIOD = 100 * 12
    private var ROBUSTNESS = 10
    private var EYESIGHT = 200
    private val STRENGTH = intArrayOf(0, 100 * 12, 100 * 24 * 2)
    private val INCUBATIONPERIOD = 100 * 12
    private var MAXBABIES = 10
}