package simyukkuri

import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.image.Image
import java.nio.file.Files
import java.nio.file.Paths


/** 画面のコントローラー */
class GamePaneController {
    /** ゲームシーンを表示するキャンバス */
    @FXML
    lateinit var gameSceneCanvas: Canvas

    /** ゲームシーンを表示するキャンバスの[GraphicsContext] */
    lateinit private var gc: GraphicsContext

    /** プレイヤーの行動のリストの表示 */
    @FXML
    lateinit var mainMenu: ComboBox<MainMenu>

    /** テスト用の文章 */
    @FXML
    lateinit var testLabel: Label

    /** 背景画像 */
    private val bgImage = Image(Files.newInputStream(Paths.get("images\\back.jpg")), 568.0, 432.0, true, true)


    /** プレイヤーの行動 */
    enum class MainMenu {
        TOOL, FOOD, CLEAN, OKAZARI, PUT_UP, WRAPPER, TOILET, WALL, TOY, CONVEYOR_BELT, BLEEDING_POOL, DUST_CHUTE,
        PRESS_MACHINE, FOOD_MAKER, ORANGE_POOL, PRODUCT_CHUTE
    }


    /**
     * 初期化処理.
     *
     * 自動的に呼び出される.
     */
    @FXML
    fun initialize() {
        mainMenu.items = FXCollections.observableList(MainMenu.values().toList())
        gc = gameSceneCanvas.graphicsContext2D
        gc.drawImage(bgImage, 0.0, 0.0)
    }


    /** ゆっくり追加ボタンを押したときの処理. */
    @FXML
    fun addYukkuri(e: ActionEvent) {
        testLabel.text = "ゆっくりを追加してえ"
    }


    // 中身はGameSceneに書いてそれを呼び出すほうがいいかもしれない.
    /** 現在の状態をセーブする. */
    @FXML
    fun save(e: ActionEvent) {
    }

    /** 現在の状態をロードする. */
    @FXML
    fun load(e: ActionEvent) {
    }
}
