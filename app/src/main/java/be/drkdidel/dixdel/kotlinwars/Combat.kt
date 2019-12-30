package be.drkdidel.dixdel.kotlinwars

import android.content.Context
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.nextInt

class Combat(val view: Context, val textView: TextView) {

    private var output = ArrayList<String>()

    private val lesBons: Equipe = Equipe("les bons")
    private val lesMauvais: Equipe = Equipe("les mauvais")

    private var cptRound: Int = 0
    private var firstAttacker: Equipe = lesBons
    private var secondAttacker: Equipe = lesMauvais

    init {
        output.add(lesBons.toString())
        output.add(lesMauvais.toString())
        output.add("")
    }

    fun playRound(): Boolean {
        cptRound++
        output.add("Round $cptRound")
        output.add("--------")

        drawTurns()

        firstAttacker.getFightingTeam()
        secondAttacker.getFightingTeam()
        output.add(firstAttacker.getOutput())
        output.add(secondAttacker.getOutput())

        launchAttack(firstAttacker, secondAttacker)

        if (secondAttacker.isStillFighting()) {
            output.add("Riposte !")
            launchAttack(secondAttacker, firstAttacker)
        }

        firstAttacker.dismissFightingTeam()
        secondAttacker.dismissFightingTeam()

        output.add("")
        return !firstAttacker.isStillFighting() || !secondAttacker.isStillFighting()
    }

    private fun launchAttack(teamA: Equipe, teamB: Equipe) {
        teamA.attack(teamB.getFightingTeam())
        teamB.buryTheDeads()
        output.add(teamA.getOutput())
        output.add(teamB.getOutput())
    }

    private fun drawTurns() {
        when(Random.nextInt(0..1)) {
            1 -> {
                firstAttacker = lesMauvais
                secondAttacker = lesBons
            }
            else -> {
                firstAttacker = lesBons
                secondAttacker = lesMauvais
            }
        }
    }

    fun showResults() {
        output.add("Survivants après $cptRound rounds : ${lesBons.nbSurvivants()} bons, ${lesMauvais.nbSurvivants()} mauvais.")
    }

    override fun toString(): String {
        output.add("----------------")
        output.add(view.getString(R.string.fightEndMessage))
        showResults()
        return output.joinToString(System.lineSeparator())
    }

}
