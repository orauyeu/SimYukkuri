package osdnto3

import messageutil.myYaml
import java.nio.file.Files
import java.nio.file.Paths

/** osdn_oldディレクトリ内のセリフファイルを新しい形式に直してダンプしたものをプリントする. */
fun main(args: Array<String>) {
    val names = listOf(
            "Alice",
            "Marisa",
            "MarisaReimu",
            "Reimu",
            "ReimuMarisa",
            "Tarinai",
            "WasaReimu"
    )

    val name = names[1]

    val oldPath = Paths.get("osdn_old", "${name}_jp.yml")
    val (paramData, messageData) = Files.newInputStream(oldPath).use { reformatOsdnMessage(it) }

    print(myYaml.dump(messageData))
}