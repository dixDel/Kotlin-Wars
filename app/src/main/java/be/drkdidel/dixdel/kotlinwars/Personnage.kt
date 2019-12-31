package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

abstract class Personnage(private val name: String, private val number: Int) : ClassePersonnage {

    val fullname: String = "$name$number"
    val xpNextLevel = 10.0
    abstract val levelUpArmor: Int
    abstract val levelUpDamage: Int

    abstract var ptsArmorMax: Int
    open var ptsArmor: Int = 0
        set(value) {
            if (value > ptsArmorMax) {
                field = ptsArmorMax
            } else {
                field = value
            }
        }
    abstract var damageMin: Int
    abstract var damageMax: Int
    abstract var chanceCritical: Int
    abstract var criticalMultiplier: Int
    var xp = 0.0
    var level = 1
    var output = ArrayList<String>()

    override fun attack(foe: Personnage) {
        var damage = Random.nextInt(damageMin..damageMax)
        if (Random.nextInt(1..100) <= chanceCritical) {
            damage = criticalAction(damage)
        }
        if (damage > 0) {
            output.add("$fullname a infligé à ${foe.fullname} $damage pts de dommage !")
            foe.reduceArmor(damage)
            gainXp(damage)
        }
    }

    private fun gainXp(damage: Int) {
        xp += damage / damageMax * 5
        if (xp >= xpNextLevel) {
            levelUp()
            xp = 0.0
        }
    }

    private fun levelUp() {
        level++
        ptsArmorMax += levelUpArmor
        ptsArmor += levelUpArmor
        damageMin += levelUpDamage
        damageMax += levelUpDamage
        output.add("LEVEL UP ! $fullname est niveau $level ! Dmg: $damageMin à $damageMax; $ptsArmor/$ptsArmorMax pts d'armure.")
    }

    override fun criticalAction(originalDamage: Int): Int {
        output.add("$fullname a fait un coup CRITIQUE !")
        return originalDamage * criticalMultiplier
    }

    override fun reduceArmor(damage: Int) {
        ptsArmor -= damage
        output.add("$fullname a $ptsArmor/$ptsArmorMax pts d'armure.")
    }

    override fun isHurt(): Boolean {
        return ptsArmor < ptsArmorMax
    }

    override fun isKilled(): Boolean {
        return ptsArmor <= 0
    }

    override fun toString(): String {
        return "$fullname: $ptsArmor/$ptsArmorMax pts d'armure"
    }

    fun getOutput(): String {
        val finalOutput = output.joinToString(System.lineSeparator())
        cleanOutput()
        return finalOutput
    }

    fun cleanOutput() {
        output.clear()
    }
}
