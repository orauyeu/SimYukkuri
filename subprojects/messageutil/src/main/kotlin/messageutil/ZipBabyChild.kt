package messageutil

// TODO: コメントなし版を作る.
/** 赤ゆと子ゆで重複している発言を[Growth.BABY_OR_CHILD]にまとめる. */
fun zipBabyChild(msgData: CommentedMessageCollection): CommentedMessageCollection =
    linkedMapOf<String, Commented<LinkedHashMap<Condition, MutableList<String>>>>().also {
        for ((key, commentedStatsToMsgs) in msgData) {
            val (commentLines, statsToMsgs) = commentedStatsToMsgs
            val newStatsToMsgs = linkedMapOf<Condition, MutableList<String>>()
            it.put(key, Commented(commentLines, newStatsToMsgs))

            for ((stats, msgs) in statsToMsgs) {
                when {
                    stats.growth == Growth.BABY -> {
                        // 同じメッセージがCHILDにもあるときBABY_OR_CHILDに追加
                        val toBabyOrChild = stats.copy(growth = Growth.BABY_OR_CHILD)
                        val toChild = stats.copy(growth = Growth.CHILD)
                        for (msg in msgs) {
                            if (toChild in statsToMsgs && msg in statsToMsgs[toChild]!!) {
                                if (toBabyOrChild in newStatsToMsgs && msg in newStatsToMsgs[toBabyOrChild]!!) continue
                                newStatsToMsgs.getOrPut(toBabyOrChild) { mutableListOf() }.add(msg)
                            } else
                                newStatsToMsgs.getOrPut(stats) { mutableListOf() }.add(msg)
                        }
                    }
                    stats.growth == Growth.CHILD -> {
                        // 同じメッセージがBABYにもあるときはコメントだけ追加
                        val toBabyOrChild = stats.copy(growth = Growth.BABY_OR_CHILD)
                        val toBaby = stats.copy(growth = Growth.BABY)
                        for (msg in msgs) {
                            if (toBaby in statsToMsgs && msg in statsToMsgs[toBaby]!!) {
                                if (toBabyOrChild in newStatsToMsgs && msg in newStatsToMsgs[toBabyOrChild]!!) continue
                                newStatsToMsgs.getOrPut(toBabyOrChild) { mutableListOf() }.add(msg)
                            } else
                                newStatsToMsgs.getOrPut(stats) { mutableListOf() }.add(msg)
                        }
                    }
                    else -> for (message in msgs)
                        it[key]!!.body.getOrPut(stats) { mutableListOf() }.add(message)
                }
            }
        }
    }
