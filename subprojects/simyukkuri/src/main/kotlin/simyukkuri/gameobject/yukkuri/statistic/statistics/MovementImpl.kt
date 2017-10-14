package simyukkuri.gameobject.yukkuri.statistic.statistics

import simyukkuri.GameScene
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.action.actions.Sleep
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStat
import simyukkuri.geometry.HasPosition3
import simyukkuri.geometry.Position3

/** [Movement]の標準的ゆっくりへの実装 */
class MovementImpl(x: Double, y: Double, z: Double) : Movement {
    companion object {
        /** 重力による1秒あたりの加速 */
        private const val gravAccel = 5.0

        /** 現在のフィールド */
        lateinit var gameScene: GameScene
    }

    lateinit var self: YukkuriStat

    override var position: Position3
        get() = Position3(x, y, z)
        set(value) {
            x = value.x
            y = value.y
            z = value.z
        }

    // TODO: マップからはみ出ないようにする.
    override var x = x
    override var y = y
    override var z = z

    override val radius: Double
        get() = 128.0

    override var height = 128.0

    /** x方向の速さ */
    var vx = 0.0
    /** y方向の速さ */
    var vy = 0.0
    /** z方向の速さ */
    var vz = 0.0
    // TODO: ベルトコンベアに対応して床の速度を表す変数を作る

    /** ジャンプの高さ */
    protected val jumpHeight = 50.0

    /** 1秒あたりに進むことのできる距離 */
    override val speed = 10.0

    /** 掴まれているか */
    var isGrabbed = false

    /** 移動の目的地 */
    var destination: HasPosition3? = null

    // プレイヤーが配置した壁の処理を一旦消したのでまた加える.
    // TODO: 移動後の座標を反映する.
    /** 慣性による移動や衝突ダメージの処理を行う. */
    protected fun move() {
        // 衝突によるダメージ
        var collisionDamage = 0.0

        if (isGrabbed) {
            return
        }

        var x = position.x + vx
        if (x < 0.0) {
            collisionDamage += Math.abs(vx)
            x = 0.0
            vx = -vx
        } else if (x > gameScene.maxX) {
            collisionDamage += Math.abs(vx)
            x = gameScene.maxX
            vx = -vx
        }

        var z = position.z + vz
        if (z < 0.0) {
            collisionDamage += Math.abs(vz)
            z = 0.0
            vz = -vz
        } else if (z > gameScene.maxZ) {
            collisionDamage += Math.abs(vz)
            z = gameScene.maxZ
            vz = -vz
        }

        var y = position.y + vy
        if (y > 0.0) {
            vy -= gravAccel * Time.UNIT
        } else {
            collisionDamage += Math.abs(vy)
            // 摩擦で停止
            // TODO: 摩擦による減速に置き換える
            z = 0.0
            vz = 0.0
            vy = 0.0
            vx = 0.0
        }

        // 1.5は適当
        if (collisionDamage >= jumpHeight * 1.5) {
            self.damageParam += (collisionDamage - jumpHeight * 1.5).toFloat()
            self.says(self.msgList.scream)
            // プレイヤーしかこのタイプのダメージを与えられないことが前提
            self.getAngry()
            // TODO: 多分これではちゃんと動作していない.
            if (self.action == Sleep(self))
                self.action.interrupt()
            if (self.isDead) {
                self.says(self.msgList.dying)
                self.isCrushed = true
            }
        }
    }

    override fun update() {
        move()
    }
}
