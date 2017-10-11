package shitarabato3

import messageutil.messageDataToString
import messageutil.zipBabyChild
import java.nio.file.Files
import java.nio.file.Paths

/** kakojo_oldディレクトリ内のセリフファイルを新しい形式に直してダンプしたものをプリントする. */
fun main(args: Array<String>) {
    val oldDir = Paths.get("shitaraba_old")

    for (oldFilePath in Files.list(oldDir)) {
        Files.newBufferedReader(oldFilePath).use {
            try {
                parseKakojoMessage(it, strict = false)
            } catch (e: Exception) {
                throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
            }
        }
            .let { zipBabyChild(it) }
            .let { messageDataToString(it) }
            .let { println(it) }
        break
    }
}