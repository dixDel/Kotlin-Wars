package be.drkdidel.dixdel.kotlinwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var previousPositionMatch = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            //@todo case insensitive
            //  search backwards
            // search from current scroll position
            var positionMatch = fightOutputTextView.text.indexOf(searchEditText.text.toString(), previousPositionMatch)
            previousPositionMatch = positionMatch + 1
            var lineNumber = fightOutputTextView.layout.getLineForOffset(positionMatch)
            scrollView.scrollTo(0, fightOutputTextView.layout.getLineTop(lineNumber))
        }

        fightButton.setOnClickListener {
            runFight()
        }

        scrollDownButton.setOnClickListener {
            scrollView.fullScroll(ScrollView.FOCUS_DOWN)
            scrollUpButton.visibility = View.VISIBLE
            it.visibility = View.INVISIBLE
        }

        scrollUpButton.setOnClickListener {
            scrollView.fullScroll(ScrollView.FOCUS_UP)
            scrollDownButton.visibility = View.VISIBLE
            it.visibility = View.INVISIBLE
        }

        runFight()

    }

    private fun runFight() {
        val fight = Combat(this, fightOutputTextView)
        var isMatchEnded = false

        scrollView.fullScroll(ScrollView.FOCUS_UP)

        while (!isMatchEnded) {
            isMatchEnded = fight.playRound()
        }

        fightOutputTextView.text = fight.toString()
        scrollDownButton.visibility = View.VISIBLE
        scrollUpButton.visibility = View.INVISIBLE
    }
}
