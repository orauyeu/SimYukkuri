package simyukkuri.gameobject.yukkuri.ai

// Terrarium.javaからcheckPartner（ゆっくりに対するあらゆる行動をまとめたメソッド）を適当に分割したクラス.
/*
 *    Copyright 2013-2015 Mimisuke
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import simyukkuri.GameScene
import simyukkuri.Time
import simyukkuri.gameobject.yukkuri.event.action.actions.*
import simyukkuri.gameobject.yukkuri.statistic.YukkuriStats
import simyukkuri.gameobject.yukkuri.statistic.statistics.Damage
import simyukkuri.gameobject.yukkuri.statistic.statistics.Emotion
import simyukkuri.gameobject.yukkuri.statistic.statistics.MiscStat
import simyukkuri.geometry.HasPosition3
/*
class RegacyAi(val gameScene: GameScene) {
    lateinit var self: YukkuriStats
    lateinit var actionManager: ActionManager

    //TODO: y>0のときを考慮するのを忘れてるのでなんとかする.

    protected fun willSukkiriWith(other: YukkuriStats): Boolean {
        if (self.canSee(other)) return false
        if (self.isSuperRaper) return true
        if (self.isRaper) return !other.isDead
        if (!self.isHorny) return false
        if (other.isDead || other.growthStage < MiscStat.GrowthStage.ADULT || other.isRelatedToByBlood(self))
            return false
        return true
    }

    protected fun sukkiri() {
        if (self.action > ActionManager.Event.SUKKIRI) {
            return
        }
        if (!self.isHorny) return
        // レイパーの場合も生きているゆっくりを優先するようにするべき？
        // TODO: レイパーはレイパーを襲わないようにする
        val target = when {
            (self.partner != null && !self.partner!!.isDead) -> self.partner
            else -> gameScene.yukkuriNearestTo(self) { willSukkiriWith(it) }
        } ?: return
        self.tryToSukkiri(target)
    }

    protected fun getScaredOfRaper() {
        val raper = gameScene.yukkuriNearestTo(self) {
            it.isRaper && self.canSee(it)
        } ?: return
        self.says(self.msgList.scareRapist)
    }

    protected fun attack() {
        if (self.action > ActionManager.Event.ATTACK) return
        if (self.damageGrade > Damage.Grade.NONE) return
        val target = gameScene.yukkuriNearestTo(self) {
            if (!self.isNearTo(it)) return@yukkuriNearestTo false
            if (!it.hasOkazari && self.hasOkazari)
                if (self.isImmoral || !self.areFamily(it))
                    return@yukkuriNearestTo true
            return@yukkuriNearestTo false
        } ?: return
        self.says(self.msgList.hateYukkuri)
        self.tryToAttack(target)
    }

    protected fun willInitiatePhysicalContactWith(other: YukkuriStats): Boolean {
        if (self.isPartnerOf(other))
            return true

        if (self.isElderSisterOf(other))
            if (!self.isAdult)
                return true
        if (self.isYoungerSisterOf(other))
            if (!self.isAdult)
                return true
        return false
    }

    // TODO: willHogeとの重複を取り除く.
    /** スキンシップをとる. */
    protected fun initiatePhysicalContact() {
        if (self.action > ActionManager.Event.PHYSICAL_CONTACT) return
        val target = gameScene.yukkuriNearestTo(self) { willInitiatePhysicalContactWith(it) } ?: return
        when {
            self.isPartnerOf(target) ->
                self.tryToSurisuri(target)

            self.isElderSisterOf(target) ->
                if (!self.isAdult)
                    tryToPeropero(target)
            self.isYoungerSisterOf(target) ->
                if (!self.isAdult)
                    self.tryToSurisuri()
        }
    }

    protected fun feelsSad() {
        if (self.action > ActionManager.Event.FEEL_SAD) return
        val target = gameScene.yukkuriNearestTo(self) { self.areFamily(it) } ?: return
        when {
            self.isParentOf(target) -> self.showSadnessForChild(target)
            self.isPartnerOf(target) -> self.showSadnessForPartner(target)
            self.isSisterOf(target) -> self.showSadnessForSister(target)
        }
    }

    protected fun getScaredOfDead() {
        val dead = gameScene.yukkuriNearestTo(self) { it.isDead } ?: return
        self.says(self.msgList.scare)
    }

    // ゲスなら行わない, 賢いなら子供でも行うなど実装.
    protected fun willTakeCareOf(other: YukkuriStats): Boolean {
        if (self.isParentOf(other))
            if (self.isAdult && !other.isAdult)
                if (other.isDirty)
                    return true
        self.isMotherOf(other)
        if (self.isAdult)
            if (other.isDirty)
                return true
        return false
    }

    protected fun takeCareOfChild() {
        val other = gameScene.yukkuriNearestTo(self) { willTakeCareOf(it) } ?: return
        when {
            self.isParentOf(other) ->
                if (self.isAdult && !other.isAdult)
                    if (other.isDirty)
                        self.tryToPeropero()
            self.isMotherOf(other) ->
                if (self.isAdult)
                    if (other.isDirty)
                        self.tryToPeropero()
        }
    }


    fun <T : HasPosition3> nearest(c: Collection<T>, pos: HasPosition3, condition: (T) -> Boolean = { true }): T? {
        var currentMinDistance: Double? = null
        var currentNearest: T? = null
        for (other in c) {
            if (other == pos) continue
            if (!condition(other)) continue
            if (currentMinDistance == null) {
                currentMinDistance = pos.distance(other)
                currentNearest = other
            } else {
                val dist = pos.distance(other)
                if (dist >= currentMinDistance) continue
                currentMinDistance = dist
                currentNearest = other
            }
        }
        return currentNearest
    }

    protected fun goesToFamily() {
        if (self.partner != null)
            if (Math.random() < 1 / 60 * Time.UNIT) {
                self.goesTo(self.partner)
            } else if (!self.isAdult && !self.sisters.isEmpty()) {
                if (Math.random() < 1 / 60 * Time.UNIT) {
                    self.goesTo(nearest(self.sisters, self))
                }
            }
    }

    fun willMoveToBePeroperoed(): Boolean {
        if (self.isDirty && !self.isAdult)
            if (!self.parents.isEmpty())
                return true
        return false
    }

    protected fun moveToBePeroperoed() {
        if (self.isBaby && self.isDirty) {
            self.parent?.let { self.goesTo(it) }
        }
    }

    fun ai() {
        if (self.isFlyingAway) return

        if (self.isSleeping) {
            if (self.isInTravail) {
                actionManager.act(Bear(self, gameScene))
            } else if (self.wantToPoo) {
                if (self.isBaby) {
                    TODO("うんうんを漏らす")
                } else {
                    actionManager.act(Poop(self))
                }
            } else {
                actionManager.proceed()
            }
            return
        }

        if (gameScene.internalYukkuris.any { it.isRaper && self.canSee(it) }) {
            getScaredOfRaper()
            val raper = gameScene.yukkuriNearestTo(self) { it.isRaper }!!
            actionManager.act(RunAway(self, raper))
        } else if (self.isInTravail) {
            actionManager.act(Bear(self, gameScene))
        } else if (self.wantToPoo) {
            actionManager.act(Poop(self))
        } else if (self.isHorny) {
            val target = findSukkiriTarget()
            if (target == null)
                actionManager.act(Search(self))
            else
                actionManager.act(Sukkiri(self, target))
        } else if (!self.isHorny && !self.isScared && self.happiness != Emotion.Happiness.VERY_SAD) {
            actionManager.act(Sleep(self))
        } else if (willMoveToBePeroperoed()) {
            actionManager.act(Move(self, self.parent!!))
        } else if (willTakeCareOfChild()) {
            takeCareOfChild()
        } else if (self.action.isTheSameAs(NoAction())) {
            actionManager.proceed()
        } else {
            killsTime()
        }
    }

    fun findSukkiriTarget(): YukkuriStats? {
        if (self.partner != null) {
            return self.partner
        } else {
            return gameScene.yukkuriNearestTo(self)
        }
    }


    // 非常事態中でも出産のタイミングが来た場合出産してしまうというようなことがあるべき.
    fun think() {
        if (self.isFlyingAway) return
        if (self.isSleeping) {
            if (self.isInTravail) {
                self.bear()
            }
            // TODO: 赤ゆのときは漏らす
            if (self.wantToPoo) {
                wakeUp()
            }
        }
        if (gameScene.internalYukkuris.any { it.isRaper && self.canSee(it) }) {
            getScaredOfRaper()
            val raper = gameScene.yukkuriNearestTo(self) { it.isRaper }
            self.runsAwayFrom(raper)
            self.action = ActionManager.Event.RUN_AWAY_FROM_RAPER
        } else if (self.willBearSoon()) {
            if (self.bear(StandBy())) {
            }
        } else if (self.wantToPoo) {
            if (self.poopStandBy) {
                self.poop()
            } else {
                self.standByPoop()
            }
            self.action = ActionManager.Event.POOP
        } else if (self.isHorny) {
            sukkiri()
            self.action = ActionManager.Event.SUKKIRI
        } else if (!self.isHorny && !self.isScared && self.happiness != Emotion.Happiness.VERY_SAD) {
            sleep()
        } else if (willMoveToBePeroperoed()) {
            moveToBePeroperoed()
            self.action = ActionManager.Event.MOVE_TO_BE_PEROPEROED
        } else if (willTakeCareChild()) {
            takeCareOfChild()
        } else if (self.action != null) {
            self.updateAction()
        } else {
            killsTime()
        }
    }

    abstract fun killingTimes(): List<Action>

    fun killsTime() {
        val selectedIndex = (Math.random() * killingTimes.size).toInt()
        val selectedAction = killingTimes[selectedIndex]
        self.act(selectedAction)
    }


    private fun addBaby(x: Int, y: Int, z: Int, baby: YukkuriStats) {
        baby.x = x
        baby.y = y
        baby.z = z + 1
        baby.kick(0, 5, -2)
        babyList.add(baby)
    }


    fun run() {
        // update body state
        val i = bodyList.iterator()
        while (i.hasNext()) {
            val b = i.next()
            b.putStress(bodyList.size) // YukkuriStats is getting stress according as number of bodies.
            ret = b.clockTick()
            var willContinue = false
            when (ret) {
                Obj.Event.DEAD -> willContinue = true
                Obj.Event.STILLBIRTH -> {
                    if (b.babyList.size > 0) {
                        val numOfBaby = b.babyList.size
                        for (baby in b.babyList) {
                            addBaby(b.x, b.y, b.z + b.size / 20, rnd.nextInt(numOfBaby / 4 + 1) - numOfBaby / 8, rnd.nextInt(numOfBaby / 4 + 1) - numOfBaby / 8, rnd.nextInt(numOfBaby / 4 + 1) - numOfBaby / 8 - 2, baby)
                        }
                        b.babyList.clear()
                    }
                    willContinue = true
                }
                Obj.Event.BIRTHBABY -> if (b.age % 10 == 0L) {
                    val baby = b.babyListDequeue
                    if (baby != null) {
                        addBaby(b.x, b.y, b.z + b.size / 15, baby)
                    }
                }
                Obj.Event.DOSHIT -> addShit(b.x, b.y, b.z + b.size / 15, b.ageState!!)
                Obj.Event.REMOVED -> {
                    i.remove()
                    willContinue = true
                }
                else -> {
                }
            }
            if (willContinue) continue
            // check Food
            if (!checkFood(b)) {
                // check SukkiriEvent
                if (!checkPartner(b)) {
                    // check shit
                    if (!checkShit(b)) {
                        // check toilet
                        if (!checkToilet(b)) {
                            // check sleep
                            if (!checkSleepy(b)) {
                                // check toy
                                checkToy(b)
                            }
                        }
                    }
                }
            }
        }
        // add babiesInWomb.
        if (!babyList.isEmpty()) {
            bodyList.addAll(babyList)
            babyList.clear()
        }
        operationTime += TICK.toLong()
    }
}
*/