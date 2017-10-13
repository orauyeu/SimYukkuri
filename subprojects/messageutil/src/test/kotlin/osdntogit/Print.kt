package osdntogit

import messageutil.messageutilDir
import messageutil.myYaml
import messageutil.renameMessages
import java.nio.file.Files

/** OSDN形式のセリフファイルを現行形式に直したものをプリントする. */
fun main(args: Array<String>) {
    for (oldFilePath in Files.list(messageutilDir.resolve("osdn/old"))) {
        val (paramData, rawMessageData) = reformatOsdnMessage(oldFilePath)
        val messageString = rawMessageData
            .let { renameMessages(it) }
            .let { myYaml.dump(it) }
            .replace("\n", "\r\n")
        println(myYaml.dump(messageString))
    }
}