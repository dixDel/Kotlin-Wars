package be.drkdidel.dixdel.kotlinwars

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var previousPositionMatch = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            //@todo
            //  search backwards
            // search from current scroll position
            val criteria = searchEditText.text.toString()
            val positionMatch = fightOutputTextView.text.indexOf(criteria, previousPositionMatch, ignoreCase = true)
            previousPositionMatch = positionMatch + 1
            val lineNumber = fightOutputTextView.layout.getLineForOffset(positionMatch)

            val highlighted = "<font color='red'>$criteria</font>"
            var fullText = fightOutputTextView.text.toString()
            fullText = fullText.replace(criteria, highlighted)
            fullText = fullText.replace(System.lineSeparator(), "<br/>")
            fightOutputTextView.text = Html.fromHtml(fullText, Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE)

            scrollView.scrollTo(0, fightOutputTextView.layout.getLineTop(lineNumber))
        }

        // @TODO get indexOf starting from int matching scrollY
        /*
        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY -> ???
        ) }
         */

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
