package osdntogit

import messageutil.*
import java.nio.file.Files
import java.nio.file.Path

/**
 * OSDN版のセリフファイルをYAMLに変換したものから, 新形式のセリフファイルとMessageクラスのソースファイルを作成する.
 *
 * osdn/old内のセリフファイルをそれぞれ新しい形式に直してosdn/newに保存する.
 * その後, osdn/new内のまりさのセリフデータからMessagesクラスのソースファイルを作成し, osdnディレクトリに保存する.
 */
fun main(args: Array<String>) {
    messageutilDir.resolve("osdn").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("osdn/new").let { if (Files.notExists(it)) Files.createDirectory(it) }
    messageutilDir.resolve("osdn/old").let { if (Files.notExists(it)) Files.createDirectory(it) }

    writeOsdnMessages(messageutilDir.resolve("osdn/new"), messageutilDir.resolve("osdn/old"))
    writeClassSource(messageutilDir.resolve("osdn"), messageutilDir.resolve("osdn/new/marisa.yml"))
}

/** [readDir]内のOSDN版形式のセリフファイルをYAMLに変換したものを, 新しい形式に変換して[writeDir]に保存する. */
fun writeOsdnMessages(writeDir: Path, readDir: Path) {
    for (oldFilePath in Files.list(readDir)) {
        println(oldFilePath)
        val (paramData, rawMessageData) = reformatOsdnMessage(oldFilePath)
        val messageString = rawMessageData
            .let { renameMessages(it, osdnRenameMap) }
            .let { myYaml.dump(it) }
            .replace("\n", "\r\n")

        val newFileName = oldFilePath.fileName.toString().split('_')[0]
        Files.newBufferedWriter(writeDir.resolve("$newFileName.yml")).use { it.write(messageString) }
    }
}