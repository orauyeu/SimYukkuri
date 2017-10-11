package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.Time

class MessageImpl : Message {
    override var message: String? = null

    /** メッセージ表示を終了するまでの残り時間 */
    private var messagePeriod: Float = 0f

    override fun says(message: String) {
        says(message, Time.UNIT)
    }

    override fun says(message: String, period: Float) {
        this.message = message
        messagePeriod = period
    }

    override fun update() {
        messagePeriod -= Time.UNIT
        if (messagePeriod < 0f) {
            messagePeriod = 0f
            message = null
        }
    }
}