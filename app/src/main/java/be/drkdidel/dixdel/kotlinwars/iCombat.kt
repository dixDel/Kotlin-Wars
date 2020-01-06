package be.drkdidel.dixdel.kotlinwars

interface iCombat {

    fun playRound(): Boolean
    fun showResults()
    override fun toString(): String
}