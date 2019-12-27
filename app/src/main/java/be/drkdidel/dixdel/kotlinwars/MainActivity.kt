package be.drkdidel.dixdel.kotlinwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fight = Combat()
        var isMatchEnded = false

        while (!isMatchEnded) {
            isMatchEnded = fight.playRound()
        }

        fightOutputTextView.text = "Guerre terminée !"
        fight.showResults(fightOutputTextView)
    }
}
