package shitarabatogit

import messageutil.*
import java.nio.file.Files

/** OSDN形式のセリフファイルを現行形式に直したものをプリントする. */
fun main(args: Array<String>) {
    for (oldFilePath in Files.list(messageutilDir.resolve("shitaraba/old"))) {
        println(oldFilePath)
        val messageString = try {
            parseShitarabaMessage(oldFilePath, strict = false)
        } catch (e: Exception) {
            throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
        }
            .let { zipBabyChild(it) }
            .let { renameCommentedMessages(it, shitarabaRenameMap) }
            .let { messageCollectionToYaml(it) }
        print(messageString)
        break
    }
}