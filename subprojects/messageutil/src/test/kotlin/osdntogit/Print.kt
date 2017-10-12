package osdntogit

import messageutil.myYaml
import messageutil.renameMessages
import java.nio.file.Files
import java.nio.file.Paths

/** OSDN形式のセリフファイルを現行形式に直したものをプリントする. */
fun main(args: Array<String>) {
    for (oldFilePath in Files.list(Paths.get("osdn/old"))) {
        val (paramData, rawMessageData) = reformatOsdnMessage(oldFilePath)
        val messageString = rawMessageData
            .let { renameMessages(it) }
            .let { myYaml.dump(it) }
            .replace("\n", "\r\n")
        println(myYaml.dump(messageString))
    }
}