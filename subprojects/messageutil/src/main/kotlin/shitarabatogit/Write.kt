package shitarabatogit

import messageutil.*
import java.nio.file.Files
import java.nio.file.Path

// 重いのを直せるなら直す.
/**
 * したらば版のセリフファイルから現行形式のセリフファイルとMessageクラスのソースファイルを作成する.
 *
 * shitaraba/oldおよびshitaraba/old_nora内のセリフファイルをそれぞれ新しい形式に直してshitaraba/new, shitaraba/new_noraに保存する.
 * その後, shitaraba/newおよびshitaraba/new_nora内のまりさのセリフデータからMessagesクラスのソースファイルを作成し, shitarabaディレクトリに保存する.
 */
fun main(args: Array<String>) {
    messageutilDir.resolve("shitaraba").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("shitaraba/new").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("shitaraba/new_nora").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("shitaraba/old").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("shitaraba/old_nora").let { if (Files.notExists(it)) Files.createDirectory(it) }

    writeShitarabaMessages(messageutilDir.resolve("shitaraba/new"), messageutilDir.resolve("shitaraba/old"))
    writeShitarabaMessages(messageutilDir.resolve("shitaraba/new_nora"), messageutilDir.resolve("shitaraba/old_nora"))
    writeClassSource(
            messageutilDir.resolve("shitaraba"),
            messageutilDir.resolve("shitaraba/new/marisa.yml"),
            messageutilDir.resolve("shitaraba/new/marisa_event.yml"),
            messageutilDir.resolve("shitaraba/new_nora/marisa_event.yml")
    )
}

/** [readDir]内のしたらば版形式のセリフファイルを現行形式に変換して[writeDir]に保存する. */
fun writeShitarabaMessages(writeDir: Path, readDir: Path) {
    for (oldFilePath in Files.list(readDir)) {
        println(oldFilePath)
        val messageString = try {
            parseShitarabaMessage(oldFilePath, strict = false)
        } catch (e: Exception) {
            throw RuntimeException("次のファイルでエラーがありました: $oldFilePath", e)
        }
            .let { zipBabyChild(it) }
            .let { renameCommentedMessages(it, shitarabaRenameMap) }
            .let { messageCollectionToYaml(it) }

        val splited = oldFilePath.fileName.toString().split('_', '.')
        val newFileName = when {
            splited[1] == "ev" ->
                "${splited[0]}_event.yml"
            else ->
                "${splited[0]}.yml"
        }

        val newFilePath = writeDir.resolve(newFileName)
        Files.newBufferedWriter(newFilePath).use {
            it.write(messageString)
        }
    }
}