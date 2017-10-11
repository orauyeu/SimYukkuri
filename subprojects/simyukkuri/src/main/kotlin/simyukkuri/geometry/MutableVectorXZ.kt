package simyukkuri.geometry

// 必要な機能しか実装していない.
/** x,z成分を持つ可変な二次元ベクトル. */
data class MutableVectorXZ(var x: Double, var z: Double) {
    /** 指定されたスカラーを掛けてこのベクトルを返す. */
    fun multiply(k: Double): MutableVectorXZ {
        x *= k
        z *= k
        return this
    }

    /** このベクトルの大きさ */
    val magnitude: Double = Math.sqrt(x * x + z * z)

    /** 大きさを1にしてこのベクトルを返す. */
    fun normalize(): MutableVectorXZ {
        val magnitude = this.magnitude
        x /= magnitude
        z /= magnitude
        return this
    }

    /** 向きを逆にしてこのベクトルを返す. */
    fun reverse(): MutableVectorXZ {
        x = -x
        z = -z
        return this
    }
}