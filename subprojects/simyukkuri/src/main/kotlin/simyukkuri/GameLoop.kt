package simyukkuri

/**
 * ゲームループを行うクラス
 *
 * @property gameScene 現在のシーン
 */
class GameLoop(var gameScene: GameScene) {

    fun update() {
        // ゆっくりの更新
        for (ykr in gameScene.yukkuris)
            ykr.update()
        gameScene.update()
    }
}