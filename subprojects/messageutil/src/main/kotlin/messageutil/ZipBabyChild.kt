package messageutil

// TODO: !!をなくす.
/** 赤ゆと子ゆで重複している発言を[Growth.BABY_OR_CHILD]にまとめる. */
fun zipBabyChild(msgData: Map<String, Commented<Map<Statistics, Commented<List<String>>>>>): CommentedMessageData =
    linkedMapOf<String, Commented<LinkedHashMap<Statistics, MutableCommented<MutableList<String>>>>>().also {
        for ((key, commentedTypeToCommentedMessages) in msgData) {
            val (keyComment, typeToCommentedMessages) = commentedTypeToCommentedMessages
            it.getOrPut(key) { Commented(keyComment, linkedMapOf()) }

            for ((type, commentedMessages) in typeToCommentedMessages) {
                val (typeComment, messages) = commentedMessages
                when {
                    type.growth == Growth.BABY -> for (message in messages) {
                        // 同じメッセージがCHILDにもあるときBABY_OR_CHILDに追加
                        val toChild = type.copy(growth = Growth.CHILD)
                        if (toChild in typeToCommentedMessages && message in typeToCommentedMessages[toChild]!!.body) {
                            val toBabyOrChild = type.copy(growth = Growth.BABY_OR_CHILD)
                            it[key]!!.body.getOrPut(toBabyOrChild) { MutableCommented(typeComment, mutableListOf()) }
                            it[key]!!.body[toBabyOrChild]!!.body.add(message)
                        } else {
                            it[key]!!.body.getOrPut(type) { MutableCommented(typeComment, mutableListOf()) }
                            it[key]!!.body[type]!!.body.add(message)
                        }
                    }
                    type.growth == Growth.CHILD -> for (message in messages) {
                        // 同じメッセージがBABYにもあるときはコメントだけ追加
                        val toBaby = type.copy(growth = Growth.BABY)
                        if (toBaby in typeToCommentedMessages && message in typeToCommentedMessages[toBaby]!!.body) {
                            val toBabyOrChild = type.copy(growth = Growth.BABY_OR_CHILD)
                            it[key]!!.body[toBabyOrChild]!!.addCommentLine(typeComment)
                        } else {
                            it[key]!!.body.getOrPut(type) { MutableCommented(typeComment, mutableListOf()) }
                            it[key]!!.body[type]!!.body.add(message)
                        }
                    }
                    else -> for (message in messages) {
                        it[key]!!.body.getOrPut(type) { MutableCommented(typeComment, mutableListOf()) }
                        it[key]!!.body[type]!!.body.add(message)
                    }
                }
            }
        }
    }