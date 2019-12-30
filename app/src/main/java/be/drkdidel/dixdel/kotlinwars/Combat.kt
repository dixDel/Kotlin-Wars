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

        var firstTeam = firstAttacker.getFightingTeam()
        var secondTeam = secondAttacker.getFightingTeam()
        output.add(firstAttacker.getOutput())
        output.add(secondAttacker.getOutput())

        firstAttacker.attack(secondTeam)
        secondAttacker.buryTheDeads()
        output.add(firstAttacker.getOutput())
        output.add(secondAttacker.getOutput())

        if (secondAttacker.isStillFighting()) {
            output.add("Riposte !")
            secondAttacker.attack(firstTeam)
            firstAttacker.buryTheDeads()
            output.add(firstAttacker.getOutput())
            output.add(secondAttacker.getOutput())
        }

        firstAttacker.dismissFightingTeam()
        secondAttacker.dismissFightingTeam()

        output.add("")
        return !firstAttacker.isStillFighting() || !secondAttacker.isStillFighting()
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
