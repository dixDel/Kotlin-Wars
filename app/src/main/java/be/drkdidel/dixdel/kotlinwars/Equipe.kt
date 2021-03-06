package be.drkdidel.dixdel.kotlinwars

import kotlin.random.Random
import kotlin.random.nextInt

class Equipe(val name: String, nbFightersMax: Int = 100) {

    private val fightingTeamSize: Int = 5
    private val nbAssassinsMax: Int = Random.nextInt(10..20)
    private val nbMagiciensMax: Int = Random.nextInt(15..25)
    private val honorSentences: HashMap<String, Array<String>> = hashMapOf(
        "Paladin" to arrayOf(
            "%s a rendu l’âme...",
            "%s a rendu les armes...",
            "%s n’est plus, honorez sa mémoire !",
            "%s mange les pissenlits par la racine.",
            "Rest in pieces, %s"
        ),
        "Magicien" to arrayOf(
            "%s n’est plus, son bâton s’est brisé...",
            "Non, tu n’étais pas Gandalf, %s.",
            "%s a déversé toute sa mana..."
        ),
        "Assassin" to arrayOf(
            "Celle-là a eu du mal à passer, %s !",
            "%s est au fond du trou.",
            "Personne ne se souviendra de ce fourbe %s..."
        )
    )

    private var fighters: ArrayList<Personnage> = ArrayList()
    private var nbPaladins: Int = 0
    private var nbMagiciens: Int = 0
    private var nbAssassins: Int = 0
    private var fightingTeam: ArrayList<Personnage> = ArrayList()
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

    fun attack(foeTeam: ArrayList<Personnage>) {
        // Kotlin fonction locale
        fun sortFoes(
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

        var isFoeFighting = true
        var cnt = 0

        output.add("L'attaque de $name commence !")
        while (isFoeFighting && cnt < fightingTeam.count()) {
            val fighter = fightingTeam[cnt]

            sortFoes(fighter, foeTeam)

            isFoeFighting = attackFoe(fighter, foeTeam)

            cnt++
        }
        output.add("")
    }

    fun buryTheDeads() {
        fightingTeam.removeAll {
            it.isKilled()
        }
    }

    fun dismissFightingTeam() {
        fighters.addAll(fightingTeam)
        fightingTeam.clear()
    }

    private fun attackFoe(fighter: Personnage, foes: ArrayList<Personnage>): Boolean {
        val foe: Personnage? = getOpponent(foes)
        var isFoeFighting = true
        if (foe != null) {
            if (fighter is Magicien) {
                val magicien: Magicien = fighter
                magicien.team = fightingTeam
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
            giveHonorsTo(foe)
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

    fun getFightingTeam(): ArrayList<Personnage> {
        if (fightingTeam.count() == 0 && fighters.count() > 0) {
            val team = ArrayList<Personnage>()
            var hasFighters = true
            var cnt = 0
            output.add("Équipe de \"$name\"")
            while (hasFighters && cnt < fightingTeamSize) {
                val fighter = getFighter()
                fighter?.let {
                    team.add(it)
                    output.add(it.toString())
                } ?: run {
                    hasFighters = false
                    output.add("Plus de combattants !")
                }
                /*
                if (fighter != null) {
                    team.add(fighter)
                    output.add(fighter.toString())
                } else {
                    hasFighters = false
                    output.add("Plus de combattants !")
                }
                */
                cnt++
            }
            output.add("")
            fightingTeam = team
        }
        return fightingTeam
    }

    private fun getFighter(): Personnage? {
        var fighter: Personnage? = null
        if (fighters.count() > 0) {
            fighter = fighters.removeAt(Random.nextInt(fighters.count()))
        }
        return fighter
    }

    fun isStillFighting(): Boolean {
        return fighters.isNotEmpty() || fightingTeam.isNotEmpty()
    }

    fun getOutput(): String {
        val finalOutput = output.joinToString(System.lineSeparator())
        cleanOutput()
        return finalOutput
    }

    fun cleanOutput() {
        output.clear()
    }

    private fun giveHonorsTo(fighter: Personnage) {
        if (fighter.isKilled()) {
            val type = fighter.javaClass.simpleName
            val size = honorSentences[type]!!.count() - 1
            val sentence = honorSentences[type]!![Random.nextInt(0..size)]
            output.add(String.format(sentence, fighter.fullname))
        }
    }
}
