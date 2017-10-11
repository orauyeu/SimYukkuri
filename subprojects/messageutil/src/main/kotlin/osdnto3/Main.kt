package osdnto3

import messageutil.myYaml
import java.nio.file.Files
import java.nio.file.Paths

/** osdn_oldディレクトリ内のセリフデータを新しい形式に直してosdn_newディレクトリに保存する. */
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

    for (name in names) {
        val oldPath = Paths.get("osdn_old", "${name}_jp.yml")
        val (paramData, messageData) = Files.newInputStream(oldPath).use { reformatOsdnMessage(it) }

        val path = Paths.get("osdn_new", "$name.yml")
        Files.newBufferedWriter(path).use {
            myYaml.dump(messageData, it)
        }
    }
}