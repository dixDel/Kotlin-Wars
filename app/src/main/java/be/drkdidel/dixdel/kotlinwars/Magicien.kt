package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Magicien(faction: String, nbMagiciens: Int) : Personnage("$faction - Mago", nbMagiciens) {

    override val ptsArmorMax: Int = Random.nextInt(25..50)
    val healRatio = 50 // % de son total de vie

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

    var team = ArrayList<Personnage>()

    override fun criticalAction(originalDamage: Int): Int {
        var finalDamage = 0
        if (!heal()) {
            finalDamage = super.criticalAction(originalDamage)
        }
        return finalDamage
    }

    private fun heal(): Boolean {
        var hasHealed = false
        var hurtFighter = getFighterToHeal()
        if (hurtFighter != null) {
            var restored = ptsArmor * healRatio / 100
            hurtFighter.ptsArmor += restored
            output.add("HEAL ! $fullname a rendu $restored pts à ${hurtFighter.fullname} : ${hurtFighter.ptsArmor}/${hurtFighter.ptsArmorMax} pts d'armure.")
            hasHealed = true
        }
        return hasHealed
    }

    private fun getFighterToHeal(): Personnage? {
        var fighterToHeal: Personnage? = null
        var percentLifeMin = 100
        var percentLifeLeft: Int
        output.add("$fullname vérifie s'il y a un personnage à soigner...")
        for (fighter in team) {
            if (fighter.fullname != fullname) {
                percentLifeLeft = fighter.ptsArmor * 100 / fighter.ptsArmorMax
                output.add("${fighter.fullname} a $percentLifeLeft% de pts d'armure restants ${fighter.ptsArmor}/${fighter.ptsArmorMax}")
                if (percentLifeLeft < percentLifeMin) {
                    percentLifeMin = percentLifeLeft
                    fighterToHeal = fighter
                } else if (percentLifeLeft < 100 && percentLifeLeft == percentLifeMin) {
                    if (fighter.ptsArmor < fighterToHeal!!.ptsArmor) {
                        fighterToHeal = fighter
                    }
                }
            }
        }
        return fighterToHeal
    }

}
