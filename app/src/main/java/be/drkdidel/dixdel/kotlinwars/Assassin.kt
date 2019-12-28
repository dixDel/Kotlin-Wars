package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Assassin(faction: String, number: Int) : Personnage("$faction - Ass", number) {

    override val ptsArmorMax: Int
        get() = Random.nextInt(35..75)
    override var ptsArmor = ptsArmorMax
    override var damageMin: Int
        get() = 15
        set(value) {}
    override var damageMax: Int
        get() = 30
        set(value) {}
    override var chanceCritical: Int
        get() = 30
        set(value) {}
    override var criticalMultiplier: Int
        get() = 2
        set(value) {}

}
