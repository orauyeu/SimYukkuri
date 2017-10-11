package simyukkuri.resource

// メッセージの欠けの補完はここで行う.
// 例えば, ダメージを受けている版のメッセージがない場合, 代わりに通常版のメッセージを返すなど.
// TODO: ダメージを受けてる版のメッセージをプロパティに追加する.
abstract class Messages {
    val wantFood: String
    val noFood: String
    val excite: String
    val relax: String
    val furifuri: String
    val wakeUp: String
    val sleep: String
    val scream: String
    val scare: String
    val scareRapist: String
    val alarm: String
    val dying: String
    val dead: String
    val eating: String
    val eatingShit: String
    val eatingBitter: String
    val full: String
    val healing: String
    val sukkiri: String
    // うんうんが出ることの予告
    val wantShit: String
    val wetting: String
    val shitting: String
    val wetted: String
    val surisuri: String
    val peropero: String
    val breeding: String
    val breed: String
    val abort: String
    val hateShit: String
    val hungry: String
    val lostAccessory: String
    val hateYukkuri: String
    val flying: String
    val sadnessForChild: String
    val sadnessForPartner: String
    val sadnessForElderSister: String
    val sadnessForYoungerSister: String
    val blockedByWall: String
    val getTreasure: String
    val lostTreasure: String
    val nobinobi: String
}