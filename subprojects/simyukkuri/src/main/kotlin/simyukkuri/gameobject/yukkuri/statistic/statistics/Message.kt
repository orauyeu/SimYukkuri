package simyukkuri.gameobject.yukkuri.statistic.statistics

/** ゆっくりの発言のインターフェース */
interface Message {
    /** 現在発言しているセリフ */
    val message: String?

    /**
     * セリフを1tick表示する.
     *
     * このメソッド使った発言は他のアクションと並行して行われる.
     * 発言の終了後にアクションを行いたい場合は[simyukkuri.gameobject.yukkuri.event.action.actions.Say]を利用する.
     */
    fun says(message: String)

    /**
     * セリフを指定された秒数表示する.
     *
     * このメソッド使った発言は他のアクションと並行して行われる.
     * 発言の終了後にアクションを行いたい場合は[simyukkuri.gameobject.yukkuri.event.action.actions.Say]を利用する.
     */
    fun says(message: String, period: Float)

    fun update()
}
