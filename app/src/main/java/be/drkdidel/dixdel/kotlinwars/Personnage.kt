package be.drkdidel.dixdel.kotlinwars

abstract class Personnage(private val name: String, private val number: Int) : ClassePersonnage {

    val fullname: String = "$name - $number"
    var ptsArmorMax = 0
    var ptsArmor: Int = 0
        set(value) {
            if (value > ptsArmorMax) {
                field = ptsArmorMax
            } else {
                field = value
            }
        }
    var ptsAttack = 0
    var chanceCritical = 0
    var criticalMultiplier = 0
    var xp = 0.0
    var damageMin = 0
    var damageMax = 0

    override fun attack(foe: Personnage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reduceArmor(damage: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isHurt(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isKilled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return "$name: $ptsArmor pts d'armure"
    }
}
