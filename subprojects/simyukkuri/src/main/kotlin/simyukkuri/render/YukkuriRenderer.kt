package simyukkuri.render

import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.Yukkuri
import simyukkuri.gameobject.yukkuri.statistic.statistics.Growth
import simyukkuri.geometry.RectangleXY
import simyukkuri.geometry.SizeXZ
import java.util.*

// TODO: Actionクラスの描画データを反映する.
/** ゆっくりの描画クラス */
open class YukkuriRenderer(val yukkuri: Yukkuri, val currentMapSize: SizeXZ) : Renderer {

    companion object {
        private val random = Random()

        // TODO: ゆっくりのサイズ反映
        val jump = intArrayOf(0, 8, 12, 14, 15, 14, 12, 8, 0)
        // それぞれの動作でどれだけ動くかのテーブル.
        // TODO: GrowthStage.ordinalへの依存をなくす.
        // TODO: 割り算じゃなく掛け算を使う.
        val jumpMag = EnumMap<Growth.GrowthStage, Double>(mapOf(Pair(Growth.GrowthStage.BABY, 0.5), Pair(Growth.GrowthStage.CHILD, 0.5), Pair(Growth.GrowthStage.ADULT, 1.0)))
        val boundX = doubleArrayOf(0.00, -0.04, -0.02, -0.01, 0.00, 0.00, 0.00, 0.00, 0.08)
        val boundY = doubleArrayOf(0.00, 0.04, 0.02, 0.01, 0.00, 0.00, 0.00, 0.00, -0.08)
        val nobinobiY = doubleArrayOf(0.0, 0.1, 0.15, 0.17, 0.18, 0.17, 0.15, 0.1, 0.0)
        val surisuriY = doubleArrayOf(0.0, 0.05, 0.07, 0.08, 0.08, 0.08, 0.07, 0.05, 0.0)
        val sleepingY = doubleArrayOf(0.0, 0.01, 0.02, 0.03, 0.03, 0.03, 0.02, 0.01, 0.0)
    }


    private val images = yukkuri.images


    /**
     * ゆっくりの体型を反映した描画長方形を返す.
     */
    private fun resizeByFigure(rect: RectangleXY): RectangleXY {
        // x方向の倍率
        var mag = 1.0
        if (yukkuri.isRude) {
            mag *= 1.1
        }
        if (yukkuri.isPregnant) {
            mag *= 1.1
        }
        if (yukkuri.isHungry) {
            mag *= 0.9
        }
        return rect.multiplied(mag, 1.0)
    }


    private fun renderShowingAnus(myGc: MyGraphicsContext, rect: RectangleXY) {
        myGc.drawImage(images.showAnus, rect)
        if (yukkuri.hasWrapper) {
            myGc.drawImage(images.wrapperFront, rect)
        }
        if (yukkuri.hasOkazari) {
            myGc.drawImage(images.okazariFront, rect)
        }
    }

    private fun renderFurifuri(myGc: MyGraphicsContext, rect: RectangleXY, currentTick: Long) {
        val frame = Time.frameIndex(4, 2, currentTick)
        when (frame) {
            0 -> {
                myGc.drawImage(images.rollLeft, rect)
                if (yukkuri.hasWrapper) {
                    myGc.drawImage(images.wrapperLeft, rect)
                }
                if (yukkuri.hasOkazari) {
                    myGc.drawImage(images.okazariFront, rect)
                }
            }
            1 -> {
                myGc.drawImage(images.rollRight, rect)
                if (yukkuri.hasWrapper) {
                    myGc.drawImage(images.wrapperRight, rect)
                }
                if (yukkuri.hasOkazari) {
                    myGc.drawImage(images.okazariFront, rect)
                }
            }
        }
    }

    private fun renderCrushed(myGc: MyGraphicsContext, rect: RectangleXY) {
        if (yukkuri.hasOkazari) {
            myGc.drawImage(images.crushed, rect)
        } else {
            myGc.drawImage(images.crushedWithoutOkazari, rect)
        }
    }


    /** 横向き時の顔の画像 */
    private fun sidewaysFaceImage(): Image {
        return when {
        // 身体による分岐
            yukkuri.isDead -> images.dead
            yukkuri.isDoingNobinobi -> images.smile
            yukkuri.isDoingSurisuri -> images.smile
            yukkuri.isExciting -> images.exciting
            yukkuri.isSleeping -> images.sleeping
            yukkuri.isDoingPeropero || yukkuri.isEating -> {
                when {
                    yukkuri.isStruck || yukkuri.isVerySad || yukkuri.feelsHardPain ->
                        images.crying
                    yukkuri.isSad || yukkuri.isEatingPoop || yukkuri.feelsPain ->
                        images.tired
                    else ->
                        images.smile
                }
            }
            yukkuri.isSukkiring -> images.refreshed
            yukkuri.isDamaged || yukkuri.isSick || yukkuri.feelsPain -> {
                if (yukkuri.isStruck || yukkuri.isVerySad || yukkuri.feelsHardPain)
                    images.crying
                else
                    images.tired
            }
        // 精神による分岐
            yukkuri.z != 0.0 ->
                images.cheer
            yukkuri.isStruck || yukkuri.isVerySad ->
                images.crying
            yukkuri.isAngry ->
                images.puff
            yukkuri.isSad || yukkuri.isOld ->
                images.tired
            yukkuri.isHappy ->
                images.smile
            yukkuri.isTalking && yukkuri.isRude ->
                images.rude
            yukkuri.isTalking && !yukkuri.isRude ->
                images.cheer
        // 50分の1の確率で瞬き
        // TODO: もっと長時間目を閉じるために状態を持たせる.
            random.nextInt(50) == 0 ->
                images.sleeping
            else ->
                images.normal
        }
    }


    // TODO: 整理
    /** 横向き時のゆっくりの状態によるサイズの変化や位置のずれ */
    private fun sidewaysAnimation(): Correction {
        var bx = 1.0
        var by = 1.0
        val dx = 1.0
        var dy = 1.0
        when {
            yukkuri.isDead -> {
            }
            yukkuri.isDoingNobinobi -> {
                // 各状態が2フレーム
                by += nobinobiY[yukkuri.age.toInt() / 2 % 9]
            }
            yukkuri.isDoingSurisuri -> {
                by += surisuriY[yukkuri.age.toInt() / 2 % 9]
            }
            yukkuri.isExciting -> {
                if (!yukkuri.isGrabbed && yukkuri.z == 0.0) {
                    dy -= jump[yukkuri.age.toInt() % 9] * jumpMag[yukkuri.growthStage]!!
                    dy -= jump[yukkuri.age.toInt() % 9] * jumpMag[yukkuri.growthStage]!!
                }
                bx += boundX[yukkuri.age.toInt() % 9]
                by += boundY[yukkuri.age.toInt() % 9]
            }
            yukkuri.isSleeping -> {
                by += sleepingY[yukkuri.age.toInt() / 4 % 9]
            }
            yukkuri.isDoingPeropero || yukkuri.isEating -> {
            }
            yukkuri.isSukkiring -> {
            }
            yukkuri.isDamaged || yukkuri.isSick || yukkuri.feelsPain -> {
            }
            else -> {
                // 跳ねる
                if (!yukkuri.isGrabbed && yukkuri.z == 0.0 && !yukkuri.isStaying && !yukkuri.hasBabies) {
                    dy -= jump[yukkuri.age.toInt() % 9] / 2 * jumpMag[yukkuri.growthStage]!!
                    dy -= jump[yukkuri.age.toInt() % 9] / 2 * jumpMag[yukkuri.growthStage]!!
                    bx += boundX[yukkuri.age.toInt() % 9] / 2
                    by += boundY[yukkuri.age.toInt() % 9] / 2
                }
            }
        }
        return Correction(bx, by, dx, dy)
    }


    /** 横を向いている(普段の状態)ゆっくりを描画する. */
    private fun renderSideways(myGc: MyGraphicsContext, rect: RectangleXY) {

        val face = sidewaysFaceImage()
        val correction = sidewaysAnimation()

        var rect = rect.applid(correction)
        // 大きくなった分だけ左にずらす?

        // 身体
        myGc.drawDirectedImage(images.body, rect, !yukkuri.facingLeft)

        // 顔
        myGc.drawDirectedImage(face, rect, !yukkuri.facingLeft)

        // おかざり
        if (yukkuri.hasOkazari) {
            myGc.drawDirectedImage(images.okazari, rect, !yukkuri.facingLeft)
        }
        // ダメージ
        if (yukkuri.isDamaged || yukkuri.isOld) {
            myGc.drawDirectedImage(images.damage, rect, !yukkuri.facingLeft)
        }
        // おくるみ
        if (yukkuri.hasWrapper) {
            myGc.drawDirectedImage(images.wrapper, rect, !yukkuri.facingLeft)
        }
        // 汚れ
        if (yukkuri.isDirty) {
            myGc.drawDirectedImage(images.stain, rect, !yukkuri.facingLeft)
        }
        // ゆかび
        if (yukkuri.isSick) {
            myGc.drawDirectedImage(images.sick, rect, !yukkuri.facingLeft)
        }
        // おさげ
        myGc.drawDirectedImage(images.braid, rect, !yukkuri.facingLeft)
        // 舌
        if (yukkuri.isDoingPeropero || yukkuri.isEating || yukkuri.isEatingPoop) {
            if (yukkuri.message != null) {
                myGc.drawDirectedImage(images.lick, rect, !yukkuri.facingLeft)
            }
        }
    }


    // 種ごとに左右別画像があるかが異なる場合もあるので,
    // 画像の種類と向き->画像と反転すべきかを持ったクラス のようなメソッドと, 返り値のクラスを描画するメソッドを作る.
    // Animationクラスを作ってそこに分業させてもいいかもしれない.
    // ゆっくり側に全体としての感情を表す変数を作って中身をすっきりさせてもいいかもしれない.
    override fun render(gc: GraphicsContext, currentTick: Long) {
        var rect = SceneToCanvas.rect3ToRectXY(yukkuri.cuboid, currentMapSize)
        val myGc = MyGraphicsContext(gc)
        rect = resizeByFigure(rect)

        // 影
        myGc.drawImage(images.shadow, SceneToCanvas.rect3ToRectXY(yukkuri.cuboid.copy(z = 0.0), currentMapSize))

        // 体勢ごと
        when {
            yukkuri.isPooping || yukkuri.isBearing -> renderShowingAnus(myGc, rect)
            yukkuri.isFurifuring -> renderFurifuri(myGc, rect, currentTick)
            yukkuri.isCrushed -> renderCrushed(myGc, rect)
            else -> renderSideways(myGc, rect)
        }
    }
}

/**
 * 描画領域の拡大と移動をまとめたクラス.
 *
 * @damageParam magX x方向への拡大
 * @damageParam magY y方向への拡大
 * @damageParam shiftX x方向の移動
 * @damageParam shiftY y方向の移動
 */
data class Correction(var magX: Double, var magY: Double, var shiftX: Double, var shiftY: Double)

fun RectangleXY.applid(corr: Correction) = this.multiplied(corr.magX, corr.magY).translated(corr.shiftX, corr.shiftY)
