package be.drkdidel.dixdel.kotlinwars

class Equipe(val name: String) {

    private var nbPaladins: Int = 0
    private var nbMagiciens: Int = 0
    private var nbAssassins: Int = 0

    fun nbSurvivants(): Int {
        return 0
    }

    override fun toString(): String {
        return "Équipe $name: $nbPaladins paladins, $nbMagiciens magiciens, $nbAssassins assassins."
    }
}
