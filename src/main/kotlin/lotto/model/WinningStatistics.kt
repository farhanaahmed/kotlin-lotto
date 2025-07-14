package lotto.model

class WinningStatistics {
    private val results = mutableMapOf<Rank, Int>()

    fun calculateResult(
        tickets: Tickets,
        winningNumbers: WinningNumbers,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        tickets.toList().forEach { ticket ->
            val countOfMatch = ticket.matchCount(winningNumbers)
            val matchBonus = ticket.containsBonus(bonusNumber)
            val rank = Rank.valueOf(countOfMatch, matchBonus)
            if (rank != Rank.MISS) {
                results[rank] = results.getOrDefault(rank, 0) + 1
            }
        }
        return results.toMap()
    }

    fun calculateReturnRate(purchaseAmount: Int): Double {
        val totalPrize =
            results.entries.sumOf { (rank, count) ->
                rank.winningMoney.toLong() * count // Prevents overflow
            }

        return (totalPrize.toDouble() / purchaseAmount)
    }
}
