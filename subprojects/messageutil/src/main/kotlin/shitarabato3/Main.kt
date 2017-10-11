package shitarabato3

import messageutil.messageDataToString
import messageutil.zipBabyChild
import java.nio.file.Files
import java.nio.file.Paths

/** kakojo_oldディレクトリ内のセリフファイルを新しい形式に直してkakojo_newディレクトリに保存する. */
fun main(args: Array<String>) {
    val oldDir = Paths.get("shitaraba_old")
    val newDir = Paths.get("shitaraba_new")

    for (oldFilePath in Files.list(oldDir)) {
        val messageString = Files.newBufferedReader(oldFilePath).use {
            try {
                parseKakojoMessage(it, strict = false)
            } catch (e: Exception) {
                throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
            }
        }
            .let { zipBabyChild(it) }
            .let { messageDataToString(it) }

        val splited = oldFilePath.fileName.toString().split('_', '.')
        val newFileName = when {
            splited[1] == "ev" ->
                "${splited[0]}_event.yml"
            else ->
                "${splited[0]}.yml"
        }
        val newFilePath = newDir.resolve(newFileName)
        Files.newBufferedWriter(newFilePath).use { writer ->
            writer.write(messageString)
        }
    }
}