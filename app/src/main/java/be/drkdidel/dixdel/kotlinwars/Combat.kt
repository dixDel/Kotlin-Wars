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
        //@TODO output -> OutOfMemoryError, faut refaire display() et afficher directement
        //textView.text = lesBons.toString()
        output.add(lesBons.toString())
        output.add(lesMauvais.toString())
        output.add("")
    }

    fun playRound(): Boolean {
        cptRound++
        output.add("Round $cptRound")
        output.add("--------")

        drawTurns()

        firstAttacker.attack(secondAttacker)
        output.add(firstAttacker.getOutput())

        if (secondAttacker.isStillFighting()) {
            //secondAttacker.attack(firstAttacker)
            output.add(secondAttacker.getOutput())
        }

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

    fun display(texte: String) {
        var newText = ""
        if (textView.text != "") {
            newText = "\n"
        }
        newText += texte
        textView.text = ("${textView.text}${newText}")
    }

}
