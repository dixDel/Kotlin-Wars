package be.drkdidel.dixdel.kotlinwars

import android.widget.TextView

class Combat(val textView: TextView) {

    private val lesBons: Equipe = Equipe("les bons")
    private val lesMauvais: Equipe = Equipe("les mauvais")

    private var cptRound: Int = 0
    private var firstAttacker: Equipe = lesBons
    private var secondAttacker: Equipe = lesMauvais

    init {
        display(lesBons.toString())
        display(lesMauvais.toString())
    }

    fun playRound(): Boolean {
        cptRound++
        return true
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
