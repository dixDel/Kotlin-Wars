package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Magicien(faction: String, nbMagiciens: Int) : Personnage("$faction - Mago", nbMagiciens) {

    override val ptsArmorMax: Int
        get() = Random.nextInt(25..50)
    override var ptsArmor = ptsArmorMax
    override var damageMin: Int
        get() = 10
        set(value) {}
    override var damageMax: Int
        get() = 25
        set(value) {}
    override var chanceCritical: Int
        get() = 15
        set(value) {}
    override var criticalMultiplier: Int
        get() = 4
        set(value) {}


}
