package be.drkdidel.dixdel.kotlinwars

import android.widget.TextView
import kotlin.random.Random
import kotlin.random.nextInt

class Combat(val textView: TextView) {

    private val lesBons: Equipe = Equipe("les bons")
    private val lesMauvais: Equipe = Equipe("les mauvais")

    private var cptRound: Int = 0
    private var firstAttacker: Equipe = lesBons
    private var secondAttacker: Equipe = lesMauvais

    init {
        display(lesBons.toString())
        display(lesMauvais.toString())
        display("")
    }

    fun playRound(): Boolean {
        cptRound++
        display("Round $cptRound")
        drawTurns()
        firstAttacker.attack(secondAttacker)
        display(firstAttacker.output.joinToString("\n"))
        return true
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
        display("Survivants après $cptRound rounds : ${lesBons.nbSurvivants()} bons, ${lesMauvais.nbSurvivants()} mauvais.")
    }

    fun display(texte: String) {
        var newText = ""
        if (textView.text != "") {
            newText = "\n"
        }
        newText += texte
        textView.text = ("${textView.text}${newText}")
    }

}
