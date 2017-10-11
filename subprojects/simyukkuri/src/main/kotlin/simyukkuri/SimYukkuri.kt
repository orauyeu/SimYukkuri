package simyukkuri

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import simyukkuri.geometry.SizeXY

/** JavaFXの起動クラス. */
class SimYukkuri : Application() {
    companion object {
        /** ゲームシーンを表示するキャンバスのサイズ */
        lateinit var canvasSize: SizeXY
            private set
    }

    override fun start(stage: Stage) {
        stage.title = "しむゆっくり 3.0"

        val fxmlLoader = FXMLLoader()
        val root: Parent = fxmlLoader.load(javaClass.getResourceAsStream("simyukkuri/GamePane.fxmlmePane.fxml"))
        val scene = Scene(root)
        stage.scene = scene

        stage.show()

        val controller = fxmlLoader.getController<GamePaneController>()
        val canvas = controller.gameSceneCanvas
        canvasSize = SizeXY(canvas.width, canvas.width)
    }
}