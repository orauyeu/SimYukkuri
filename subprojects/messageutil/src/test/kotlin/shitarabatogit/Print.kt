package shitarabatogit

import messageutil.messageCollectionToYaml
import messageutil.messageutilDir
import messageutil.renameCommentedMessages
import messageutil.zipBabyChild
import java.nio.file.Files

/** kakojo_oldディレクトリ内のセリフファイルを新しい形式に直してダンプしたものをプリントする. */
fun main(args: Array<String>) {
    for (oldFilePath in Files.list(messageutilDir.resolve("shitaraba/old"))) {
        println(oldFilePath)
        val messageString = try {
            parseShitarabaMessage(oldFilePath, strict = false)
        } catch (e: Exception) {
            throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
        }
            .let { zipBabyChild(it) }
            .let { renameCommentedMessages(it) }
            .let { messageCollectionToYaml(it) }
        print(messageString)
        break
    }
}