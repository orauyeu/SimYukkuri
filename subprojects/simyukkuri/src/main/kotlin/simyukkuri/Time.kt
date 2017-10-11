package simyukkuri

/** ゲームのシステム時間と現実時間を繋ぐクラス. */
class Time {
    companion object {
        /** 標準スピードでの1秒あたりに進むゲーム内時間. tick per second */
        const val TPS = 30

        /**
         * ゲーム内時間1あたりに進む現実の時間. ただし, ゲームスピードを変更しているときは現実時間に対応するとは限らない.
         */
        const val UNIT = (1 / TPS).toFloat()
    }
}