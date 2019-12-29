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
    var output:ArrayList<String> = ArrayList()

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
        return fighters.count()
    }

    override fun toString(): String {
        return "Équipe $name: $nbPaladins paladins, $nbMagiciens magiciens, $nbAssassins assassins."
    }

    fun attack(foeTeam: Equipe) {
        var team = getFightingTeam()
        var foes = foeTeam.getFightingTeam()
        var isFoeFighting = true
        var cnt = 0

        output.add(foeTeam.getOutput())
        output.add("L'attaque de $name commence !")
        while (isFoeFighting && cnt < team.count()) {
            val fighter = team[cnt]

            sortFoes(fighter, foes)

            isFoeFighting = attackFoe(fighter, team, foes)

            cnt++
        }
        output.add("")

        dismissFightingTeam(team)
        foeTeam.dismissFightingTeam(foes)
    }

    private fun dismissFightingTeam(team: java.util.ArrayList<Personnage>) {
        team.removeAll {
            it.isKilled()
        }
        fighters.addAll(team)
    }

    private fun sortFoes(
        fighter: Personnage,
        foes: ArrayList<Personnage>
    ) {
        if (fighter is Paladin || fighter is Magicien) {
            foes.sortWith(Comparator<Personnage> { p0, p1 ->
                when {
                    p0 is Assassin && p1 is Paladin || p0 is Magicien -> 1
                    else -> -1
                }
            })
        } else {
            foes.sortWith(Comparator<Personnage> { p0, p1 ->
                when {
                    p0 is Paladin && p1 is Magicien || p0 is Assassin -> 1
                    else -> -1
                }
            })
        }
    }

    private fun attackFoe(fighter: Personnage, team: ArrayList<Personnage>, foes: ArrayList<Personnage>): Boolean {
        val foe: Personnage? = getOpponent(foes)
        var isFoeFighting = true
        if (foe != null) {
            if (fighter is Magicien) {
                val magicien: Magicien = fighter
                magicien.team = team
            }
            var ass: Assassin? = null
            if (foe is Assassin) {
                ass = foe
            }
            if (ass == null || !ass.evadeAttack(fighter)) {
                fighter.attack(foe)
                output.add(fighter.getOutput()) // pas d'output si l'attaque a été esquivée donc afficherait une ligne vide
            }
            output.add(foe.getOutput())
        } else {
            output.add("Il n'y a plus d'ennemis en état de combattre !")
            isFoeFighting = false
        }
        return isFoeFighting
    }

    private fun getOpponent(foes: java.util.ArrayList<Personnage>): Personnage? {
        return foes.firstOrNull {
            !it.isKilled()
        }
    }

    private fun getFightingTeam(): ArrayList<Personnage> {
        val team = ArrayList<Personnage>()
        var hasFighters = true
        var cnt = 0
        output.add("Équipe de \"$name\"")
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
        output.add("")
        return team
    }

    private fun getFighter(): Personnage? {
        var fighter: Personnage? = null
        if (fighters.count() > 0) {
            fighter = fighters.removeAt(Random.nextInt(fighters.count()))
        }
        return fighter
    }

    fun isStillFighting(): Boolean {
        return fighters.isNotEmpty()
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
