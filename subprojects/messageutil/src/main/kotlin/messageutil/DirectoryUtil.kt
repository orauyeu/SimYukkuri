package messageutil

import java.nio.file.Path
import java.nio.file.Paths

/** messageutilモジュールのディレクトリを返す. */
val messageutilDir: Path by lazy {
    val root = Paths.get("")
    when (root.toAbsolutePath().fileName.toString()) {
        "SimYukkuri" -> Paths.get("subprojects/messageutil")
        "messageutil" -> root
        else -> throw RuntimeException("現在のディレクトリがSimYukkuriまたはmessageutilではありません")
    }
}