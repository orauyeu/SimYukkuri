package simyukkuri.render

import javafx.scene.canvas.GraphicsContext

/** 描画クラスのインターフェース */
interface Renderer {
    fun render(gc: GraphicsContext, currentTick: Long)
}
