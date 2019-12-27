package be.drkdidel.dixdel.kotlinwars

import android.widget.TextView

class Combat {
    fun playRound(): Boolean {
        return true
    }

    fun showResults(textView: TextView) {
        var texte = textView.text
        textView.text = ("$texte\n@todo afficher résultats")
    }

}
