package simyukkuri.render

import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import simyukkuri.geometry.RectangleXY

/** [GraphicsContext]に機能を追加するラッパークラス. */
class MyGraphicsContext(val gc: GraphicsContext) {

    fun drawImage(img: Image, rect: RectangleXY) {
        gc.drawImage(img, rect.minX, rect.minY, rect.width, rect.height)
    }


    fun drawDirectedImage(img: Image, rect: RectangleXY, reversed: Boolean) {
        if (!reversed) {
            gc.drawImage(img, rect.minX, rect.minY, rect.width, rect.height)
        } else {
            gc.drawImage(img, rect.minX + rect.width, rect.minY, -rect.width, rect.height)
        }
    }
}