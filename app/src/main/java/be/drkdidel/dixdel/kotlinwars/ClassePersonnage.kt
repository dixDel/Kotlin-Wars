package be.drkdidel.dixdel.kotlinwars

interface ClassePersonnage {

    fun attack(foe: Personnage)
    fun criticalAction(originalDamage: Int): Int
    fun reduceArmor(damage: Int)
    fun isHurt(): Boolean
    fun isKilled(): Boolean

}
