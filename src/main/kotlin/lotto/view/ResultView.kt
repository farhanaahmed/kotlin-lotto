package lotto.view

import lotto.model.Lotto
import lotto.model.Rank

class ResultView {
    fun printBlankLine() {
        println()
    }

    fun printNumberOfTickets(
        numberOfManualTickets: Int,
        numberOfTickets: Int,
    ) {
        println()
        println(NUMBER_OF_TICKETS_MESSAGE.format(numberOfManualTickets, numberOfTickets))
    }

    fun printTickets(tickets: List<Lotto>) {
        tickets.forEach { ticket ->
            println(ticket)
        }
        println()
    }

    fun printStatistics(statistics: Map<Rank, Int>) {
        println()
        println("Winning Statistics")
        println("----------------")
        println(FIFTH_RANK_MESSAGE.format(statistics[Rank.FIFTH] ?: 0))
        println(FOURTH_RANK_MESSAGE.format(statistics[Rank.FOURTH] ?: 0))
        println(THIRD_RANK_MESSAGE.format(statistics[Rank.THIRD] ?: 0))
        println(SECOND_RANK_MESSAGE.format(statistics[Rank.SECOND] ?: 0))
        println(FIRST_RANK_MESSAGE.format(statistics[Rank.FIRST] ?: 0))
    }

    fun printReturnRate(returnRate: Double) {
        println("Total return rate is " + String.format("%.2f", returnRate) + " (A rate below 1 means a loss)")
    }

    companion object {
        private const val NUMBER_OF_TICKETS_MESSAGE = "Purchased %d manual and %d automatic tickets."
        private const val FIFTH_RANK_MESSAGE = "3 Matches (5,000 KRW) – %d tickets"
        private const val FOURTH_RANK_MESSAGE = "4 Matches (50,000 KRW) – %d tickets"
        private const val THIRD_RANK_MESSAGE = "5 Matches (1,500,000 KRW) – %d tickets"
        private const val SECOND_RANK_MESSAGE = "5 Matches + Bonus Ball (30,000,000 KRW) – %d tickets"
        private const val FIRST_RANK_MESSAGE = "6 Matches (2,000,000,000 KRW) – %d tickets"
    }
}
