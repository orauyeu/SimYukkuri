package osdntogit

import messageutil.myYaml
import messageutil.renameMessages
import messageutil.writeClassSource
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * OSDN版のセリフファイルをYAMLに変換したものから, 新形式のセリフファイルとMessageクラスのソースファイルを作成する.
 *
 * osdn/old内のセリフファイルをそれぞれ新しい形式に直してosdn/newに保存する.
 * その後, osdn/new内のまりさのセリフデータからMessagesクラスのソースファイルを作成し, osdnディレクトリに保存する.
 */
fun main(args: Array<String>) {
    Paths.get("osdn").let { if (Files.notExists(it)) Files.createDirectory(it) }
    Paths.get("osdn/new").let { if (Files.notExists(it)) Files.createDirectory(it) }
    Paths.get("osdn/old").let { if (Files.notExists(it)) Files.createDirectory(it) }

    writeOsdnMessages(Paths.get("osdn/new"), Paths.get("osdn/old"))
    writeClassSource(Paths.get("osdn"), Paths.get("osdn/new/marisa.yml"))
}

/** [readDir]内のOSDN版形式のセリフファイルをYAMLに変換したものを, 新しい形式に変換して[writeDir]に保存する. */
fun writeOsdnMessages(writeDir: Path, readDir: Path) {
    for (oldFilePath in Files.list(readDir)) {
        val (paramData, rawMessageData) = reformatOsdnMessage(oldFilePath)
        val messageString = rawMessageData
            .let { renameMessages(it) }
            .let { myYaml.dump(it) }
            .replace("\n", "\r\n")

        val newFileName = oldFilePath.fileName.toString().split('_')[0]
        Files.newBufferedWriter(writeDir.resolve("$newFileName.yml")).use { it.write(messageString) }
    }
}