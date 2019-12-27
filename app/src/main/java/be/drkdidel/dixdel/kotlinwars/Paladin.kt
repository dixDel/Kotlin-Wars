package be.drkdidel.dixdel.kotlinwars

class Paladin(faction: String, number: Int) : Personnage("$faction - Pal", number) {
    override fun attack(foe: Personnage) {
        super.attack(foe)
    }
}
