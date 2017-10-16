package simyukkuri.resource

import javafx.scene.image.Image
import java.nio.file.Path
import java.nio.file.Paths

// 右向きに専用画像があるときと左向きの画像を反転させて使う場合を統一的に扱えるようにする.
// 画像を反転させる方法がわからない.
// 画像を反転させずとも, 画像と反転しているかの情報を持ったクラスを作って,
// MyGraphicContextがそのクラスをいい感じに描画できるようにすれば問題ない.
/**
 * ゆっくりの画像を保持するクラス.
 */
abstract class YukkuriImageList : ImageList() {

    /** 画像を貯蔵するゆっくりのディレクトリ上での名前. */
    abstract protected val name: String

    /** 画像ディレクトリからゆっくりの画像ディレクトリへの相対パス */
    protected val yukkuriDir: Path = Paths.get("yukkuri")

    override val root: Path
        get() = imageDir.resolve(yukkuriDir).resolve(name)


    // 貯蔵される画像
    val shadow = Image(imageDir.resolve("shadow.png").toUri().toString())

    val crushed = image("crushed.png")
    val crushedWithoutOkazari = image("crushed2.png")
    val okazariFront = image("accessory.png")
    val wrapperLeft = image("left_pants.png")
    val wrapperFront = image("pants.png")
    val wrapperRight = image("right_pants.png")
    val rollLeft = image("roll_left.png")
    val rollRight = image("roll_right.png")
    val showAnus = image("poop.png")

    // 内部クラスを利用して階層構造を表してもいいかもしれない.
    // 右を向いているときは左右を反転して使うもの
    val body = image("left\\body.png")
    val braid = image("left\\braid.png")
    val damage = image("left\\damage.png")
    val lick = image("left\\lick.png")
    val okazari = image("left\\accessory.png")
    val wrapper = image("left\\pants.png")
    val shit = image("left\\poop.png")
    val sick = image("left\\sick.png")
    val stain = shit

    // 表情
    val cheer = image("left\\faces\\cheer.png")
    val crying = image("left\\faces\\crying.png")
    val dead = image("left\\faces\\dead.png")
    val exciting = image("left\\faces\\exciting.png")
    val normal = image("left\\faces\\normal.png")
    val puff = image("left\\faces\\puff.png")
    val refreshed = image("left\\faces\\refreshed.png")
    val rude = image("left\\faces\\rude.png")
    val sleeping = image("left\\faces\\sleeping.png")
    val smile = image("left\\faces\\smile.png")
    val tired = image("left\\faces\\tired.png")
}