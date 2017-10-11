package simyukkuri.render

import simyukkuri.SimYukkuri
import simyukkuri.geometry.*

// TODO: 位置を底辺中央に設定する.
/**
 * 描画処理で使う座標変換. 左手系.
 */
class SceneToCanvas {
    companion object {
        /**
         * 背景画像での地面が台形であることを考慮した画面上の位置
         *
         * @damageParam pos 変換するマップ上の位置.
         */
        fun posXZToPosXY(pos: PositionXZ, mapSize: SizeXZ): PositionXY {
            // 画像のサイズは800×600で, 台形の頂点の座標は左上を原点として,
            // (100, 100), (700, 100), (0, 600), (800, 600).

            val (x0, z0) = pos
            val (mapWidth, mapDepth) = mapSize
            val (canvasWidth, canvasHeight) = SimYukkuri.canvasSize

            // 画面の中央下を原点とする
            val x1 = x0 - mapWidth / 2
            val z1 = z0

            // 画像の高さが600に対し台形の高さが500なのに合わせy座標縮小.
            val x2 = x1
            // 画面を意識しているのでzからyに変更.
            val y2 = z1 * 500 / 600

            // y方向はそのままでx方向は歪みを補正
            // 台形の上底=600, 下底=800を考慮すると
            // yにおける横幅は 800 - 200 * y/mapHeight
            val x3 = x2 * (800 - 200 * y2 / mapDepth) / 800
            val y3 = y2

            // 原点を左上に移動
            val x4 = x3 + mapWidth / 2
            val y4 = mapDepth - y3

            //キャンバスのサイズに合わせてスケールを変える
            val x5 = x4 * canvasWidth / mapWidth
            val y5 = y4 * canvasHeight / mapDepth

            return PositionXY(x5, y5)
        }


        // マップ上のy座標から描画上のy座標へ変換する倍率を変更したい場合このメソッドを編集する.
        /**
         * 背景画像での地面が台形であることを考慮した画面上の位置.
         *
         * @damageParam pos 変換するマップ上の位置.
         */
        fun pos3ToPosXY(pos: Position3, mapSize: SizeXZ): PositionXY {
            val trans2 = posXZToPosXY(pos.deleteY(), mapSize)
            return trans2.add(0.0, -pos.y)
        }


        // マップ上の大きさから描画上の大きさに変換する倍率を変更したい場合このメソッドを編集する.
        // z方向の広がりは無視している.
        /**
         * マップ上の直方体を画面上の長方形に変換する.
         */
        fun rect3ToRectXY(rect: Cuboid, mapSize: SizeXZ): RectangleXY {
            val position = pos3ToPosXY(rect.position, mapSize)
            // 遠近法. 上のメソッドでx座標を小さくしたのと同様にサイズも小さくする.
            var size = rect.size.deleteDepth() * (600 + 200 * rect.z / mapSize.depth) / 800.0
            // マップの広さを画像のサイズに反映.
            size *= (SimYukkuri.canvasSize.width + SimYukkuri.canvasSize.height) / (mapSize.width + mapSize.depth)
            return RectangleXY(position, size)
        }


        fun sizeXZToSizeXY(size: SizeXZ): SizeXY = SizeXY(size.width, size.depth)


        fun rectXZToRectXY(rect: RectangleXZ, mapSize: SizeXZ): RectangleXY {
            val position = posXZToPosXY(rect.position, mapSize)
            var size = rect.size * (600 + 200 * rect.z / mapSize.depth) / 800.0
            size *= (SimYukkuri.canvasSize.width + SimYukkuri.canvasSize.height) / (mapSize.width + mapSize.depth)
            return RectangleXY(position, sizeXZToSizeXY(size))
        }
    }
}
