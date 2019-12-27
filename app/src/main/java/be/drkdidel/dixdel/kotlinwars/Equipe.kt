package be.drkdidel.dixdel.kotlinwars

class Equipe(val name: String) {

    private val fightingTeamSize: Int = 5

    private var nbPaladins: Int = 0
    private var nbMagiciens: Int = 0
    private var nbAssassins: Int = 0
    var output = ArrayList<String>()

    fun nbSurvivants(): Int {
        return 0
    }

    override fun toString(): String {
        return "Équipe $name: $nbPaladins paladins, $nbMagiciens magiciens, $nbAssassins assassins."
    }

    fun attack(foe: Equipe) {
        var team = getFightingTeam()
    }

    private fun getFightingTeam(): ArrayList<Personnage> {
        val team = ArrayList<Personnage>()
        var hasFighters = true
        var cnt = 0
        output.add("Équipe de ${name}")
        while (hasFighters && cnt < fightingTeamSize) {
            val fighter = getFighter()
            if (fighter != null) {
                team.add(fighter)
                output.add(fighter.toString())
            } else {
                hasFighters = false
                output.add("Plus de combattants !")
            }
            cnt++
        }
        return team
    }

    private fun getFighter(): Personnage {
        return Paladin("Pal")
    }
}
