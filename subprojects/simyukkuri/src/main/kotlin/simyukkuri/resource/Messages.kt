package simyukkuri.resource

// メッセージの欠けの補完はここで行う.
// 例えば, ダメージを受けている版のメッセージがない場合, 代わりに通常版のメッセージを返すなど.
// TODO: ダメージを受けてる版のメッセージをプロパティに追加する.
abstract class Messages {
    val findsFood: Map<Statistics, List<String>>
    val wantsFood: Map<Statistics, List<String>>
    val sexuallyExcited: Map<Statistics, List<String>>
    val relaxed: Map<Statistics, List<String>>
    val furifuri: Map<Statistics, List<String>>
    val wakesUp: Map<Statistics, List<String>>
    val sleeping: Map<Statistics, List<String>>
    val screams: Map<Statistics, List<String>>
    val scared: Map<Statistics, List<String>>
    val scaredAtRaper: Map<Statistics, List<String>>
    val alarmed: Map<Statistics, List<String>>
    val killedInstantly: Map<Statistics, List<String>>
    val dies: Map<Statistics, List<String>>
    val eating: Map<Statistics, List<String>>
    val eatingPoo: Map<Statistics, List<String>>
    val hasEatenBitter: Map<Statistics, List<String>>
    val getsFull: Map<Statistics, List<String>>
    val takesSweets: Map<Statistics, List<String>>
    val hasSukkiried: Map<Statistics, List<String>>
    val born: Map<Statistics, List<String>>
    val startsToPoop: Map<Statistics, List<String>>
    val needsToPoop: Map<Statistics, List<String>>
    val hasPooped: Map<Statistics, List<String>>
    val leaksPoo: Map<Statistics, List<String>>
    val surisuri: Map<Statistics, List<String>>
    val peropero: Map<Statistics, List<String>>
    val bearing: Map<Statistics, List<String>>
    val lamentsForAbortingBaby: Map<Statistics, List<String>>
    val complainsAboutPoo: Map<Statistics, List<String>>
    val hungry: Map<Statistics, List<String>>
    val losesOkazari: Map<Statistics, List<String>>
    val despisesYukkuriHavingNoOkazari: Map<Statistics, List<String>>
    val saysLikeFlying: Map<Statistics, List<String>>
    val childDies: Map<Statistics, List<String>>
    val partnerDies: Map<Statistics, List<String>>
    val elderSisterDies: Map<Statistics, List<String>>
    val youngerSisterDies: Map<Statistics, List<String>>
    val blockedByWall: Map<Statistics, List<String>>
    val getsTreasure: Map<Statistics, List<String>>
    val treasureTaken: Map<Statistics, List<String>>
    val nobinobi: Map<Statistics, List<String>>
}