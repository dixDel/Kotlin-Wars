package be.drkdidel.dixdel.kotlinwars

import android.util.Log
import kotlin.random.Random
import kotlin.random.nextInt

class Equipe(val name: String, nbFightersMax: Int = 100) {

    private val fightingTeamSize: Int = 5
    private val nbAssassinsMax: Int = Random.nextInt(10..20)
    private val nbMagiciensMax: Int = Random.nextInt(15..25)

    private var fighters: ArrayList<Personnage> = ArrayList()
    private var nbPaladins: Int = 0
    private var nbMagiciens: Int = 0
    private var nbAssassins: Int = 0
    var output = ArrayList<String>()

    init {
        for (i in 0..nbFightersMax) {
            if (nbAssassins < nbAssassinsMax) {
                nbAssassins++
                fighters.add(Assassin(name, nbAssassins))
            } else if (nbMagiciens < nbMagiciensMax) {
                nbMagiciens++
                fighters.add(Magicien(name, nbMagiciens))
            } else {
                nbPaladins++
                fighters.add(Paladin(name, nbPaladins))
            }
        }
    }

    fun nbSurvivants(): Int {
        return 0
    }

    override fun toString(): String {
        return "Équipe $name: $nbPaladins paladins, $nbMagiciens magiciens, $nbAssassins assassins."
    }

    fun attack(foe: Equipe) {
        var team = getFightingTeam()
        var foes = foe.getFightingTeam()
        var isFoeFighting = true
        var cnt = 0

        while (isFoeFighting && cnt < team.count()) {
            val fighter = team[cnt]

            Log.d("EQUIPE", "${fighter.fullname}: $foes")
            sortFoes(fighter, foes)
            Log.d("EQUIPE", "${fighter.fullname}: $foes")
            cnt++
        }
    }

    private fun sortFoes(
        fighter: Personnage,
        foes: ArrayList<Personnage>
    ) {
        if (fighter is Paladin || fighter is Magicien) {
            foes.sortWith(object : Comparator<Personnage> {
                override fun compare(p0: Personnage, p1: Personnage): Int = when {
                    p0 is Assassin && p1 is Paladin || p0 is Magicien -> 1
                    else -> -1
                }
            })
        } else {
            foes.sortWith(object : Comparator<Personnage> {
                override fun compare(p0: Personnage?, p1: Personnage?): Int = when {
                    p0 is Paladin && p1 is Magicien || p0 is Assassin -> 1
                    else -> -1
                }
            })
        }
    }

    private fun getFightingTeam(): ArrayList<Personnage> {
        val team = ArrayList<Personnage>()
        var hasFighters = true
        var cnt = 0
        output.add("Équipe de $name")
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

    private fun getFighter(): Personnage? {
        var fighter: Personnage? = null
        if (fighters.count() > 0) {
            fighter = fighters.removeAt(Random.nextInt(fighters.count()))
        }
        return fighter
    }
}
