package be.drkdidel.dixdel.kotlinwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fight = Combat(fightOutputTextView)
        var isMatchEnded = false

        while (!isMatchEnded) {
            isMatchEnded = fight.playRound()
        }

        fightOutputTextView.text = "${fightOutputTextView.text}\nGuerre terminée !"
        fight.showResults()
    }
}
