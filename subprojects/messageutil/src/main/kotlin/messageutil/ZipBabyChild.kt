package messageutil

/** 赤ゆと子ゆで重複している発言をbabyChildタグにまとめる. */
fun zipBabyChild(msgData: Map<String, Commented<Map<Type, Commented<List<String>>>>>): Map<String, Commented<Map<Type, Commented<List<String>>>>> {
    val collected = linkedMapOf<String, Commented<LinkedHashMap<Type, MutableCommented<MutableList<String>>>>>()

    for ((key, commentedTypeToCommentedMessages) in msgData) {
        val (keyComment, typeToCommentedMessages) = commentedTypeToCommentedMessages
        if (key !in collected)
            collected.put(key, Commented(keyComment, linkedMapOf()))

        for ((type, commentedMessages) in typeToCommentedMessages) {
            val (typeComment, messages) = commentedMessages
            when {
                type.growth == Growth.BABY -> for (message in messages) {
                    // 同じメッセージがCHILDにもあるときBABY_OR_CHILDに追加
                    val toChild = type.copy(growth = Growth.CHILD)
                    if (toChild in typeToCommentedMessages && message in typeToCommentedMessages[toChild]!!.body) {
                        val toBabyOrChild = type.copy(growth = Growth.BABY_OR_CHILD)
                        if (toBabyOrChild !in collected[key]!!.body)
                            collected[key]!!.body.put(toBabyOrChild, MutableCommented(typeComment, mutableListOf()))
                        collected[key]!!.body[toBabyOrChild]!!.body.add(message)
                    } else {
                        if (type !in collected[key]!!.body)
                            collected[key]!!.body.put(type, MutableCommented(typeComment, mutableListOf()))
                        collected[key]!!.body[type]!!.body.add(message)
                    }
                }
                type.growth == Growth.CHILD -> for (message in messages) {
                    // 同じメッセージがBABYにもあるときはコメントだけ追加
                    val toBaby = type.copy(growth = Growth.BABY)
                    if (toBaby in typeToCommentedMessages && message in typeToCommentedMessages[toBaby]!!.body) {
                        val toBabyOrChild = type.copy(growth = Growth.BABY_OR_CHILD)
                        collected[key]!!.body[toBabyOrChild]!!.addCommentLine(typeComment)
                    } else {
                        if (type !in collected[key]!!.body)
                            collected[key]!!.body.put(type, MutableCommented(typeComment, mutableListOf()))
                        collected[key]!!.body[type]!!.body.add(message)
                    }
                }
                else -> for (message in messages) {
                    if (type !in collected[key]!!.body)
                        collected[key]!!.body.put(type, MutableCommented(typeComment, mutableListOf()))
                    collected[key]!!.body[type]!!.body.add(message)
                }
            }
        }
    }
    return collected
}