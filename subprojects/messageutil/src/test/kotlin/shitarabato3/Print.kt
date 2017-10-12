package shitarabato3

import messageutil.messageDataToString
import messageutil.renameCommentedMessages
import messageutil.zipBabyChild
import java.nio.file.Files
import java.nio.file.Paths

/** kakojo_oldディレクトリ内のセリフファイルを新しい形式に直してダンプしたものをプリントする. */
fun main(args: Array<String>) {
    for (oldFilePath in Files.list(Paths.get("shitaraba/old"))) {
        println(oldFilePath)
        val messageString = try {
            parseShitarabaMessage(oldFilePath, strict = false)
        } catch (e: Exception) {
            throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
        }
            .let { zipBabyChild(it) }
            .let { renameCommentedMessages(it) }
            .let { messageDataToString(it) }
        print(messageString)
        break
    }
}