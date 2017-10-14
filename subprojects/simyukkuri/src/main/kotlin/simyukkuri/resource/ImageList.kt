package simyukkuri.resource

import javafx.scene.image.Image
import java.nio.file.Path
import java.nio.file.Paths


/**
 * ゲームオブジェクトの画像を貯蔵するクラス.
 */
abstract class ImageList {
    /** 画像ディレクトリのパス */
    protected val imageDir = Paths.get("images")

    /**
     * 画像を探すディレクトリのパス.
     *
     * 例えばれいむの画像なら "images/yukkuri/reimu"
     */
    protected abstract val root: Path

    /** [root]を基準として[path]上のイメージを取得する. */
    protected fun image(path: Path) = Image(root.resolve(path).toUri().toString())

    /** [root]を基準として[path]上のイメージを取得する. */
    protected fun image(path: String) = Image(root.resolve(path).toUri().toString())
}