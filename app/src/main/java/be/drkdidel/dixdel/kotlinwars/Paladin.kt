package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Paladin(faction: String, number: Int) : Personnage("$faction - Pal", number) {

    override val ptsArmorMax: Int
        get() = Random.nextInt(100..150)
    override var ptsArmor = ptsArmorMax
    override var damageMin = 2
    override var damageMax = 5
    override var chanceCritical = 5
    override var criticalMultiplier = 2

}
