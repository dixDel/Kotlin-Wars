package be.drkdidel.dixdel.kotlinwars

import android.widget.TextView

class Combat() {

    private val lesBons: Equipe = Equipe()
    private val lesMauvais: Equipe = Equipe()

    private var cptRound = 0

    fun playRound(): Boolean {
        cptRound++
        return true
    }

    fun showResults(textView: TextView) {
        textView.text = ("${textView.text}\n" +
                "Survivants après $cptRound rounds : ${lesBons.nbSurvivants()} bons, ${lesMauvais.nbSurvivants()} mauvais."
                )
    }

}
