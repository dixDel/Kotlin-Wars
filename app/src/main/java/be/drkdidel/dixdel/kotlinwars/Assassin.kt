package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Assassin(faction: String, number: Int) : Personnage("$faction - Ass", number) {

    override val ptsArmorMax: Int = Random.nextInt(35..75)
    val chanceEvade = 15

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

    fun evadeAttack(foe: Personnage): Boolean {
        var hasEvadedAttack = false
        if (Random.nextInt(1..100) <= chanceEvade) {
            hasEvadedAttack = true
            output.add("ESQUIVE ! $fullname a esquivé l'attaque de ${foe.fullname} !")
        }
        return hasEvadedAttack
    }

}
