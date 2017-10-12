/*
 * Copyright 2013-2015 Mimisuke,
 *           2017 <<519>> and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package simyukkuri

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import simyukkuri.geometry.SizeXY

/** メイン関数. */
fun main(args: Array<String>) {
    Application.launch(SimYukkuri::class.java, *args)
}

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