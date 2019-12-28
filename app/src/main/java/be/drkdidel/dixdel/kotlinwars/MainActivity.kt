package be.drkdidel.dixdel.kotlinwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fightButton.setOnClickListener {
            runFight()
        }

        runFight()

    }

    private fun runFight() {
        fightOutputTextView.text = ""

        val fight = Combat(fightOutputTextView)
        var isMatchEnded = false

        while (!isMatchEnded) {
            isMatchEnded = fight.playRound()
        }

        fightOutputTextView.text = String.format(
            "${fightOutputTextView.text}\n\n%s",
            getString(R.string.fightEndMessage)
        )
        fight.showResults()
    }
}
