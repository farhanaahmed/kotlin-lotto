package lotto.model

interface WinningStatistics {
    fun calculateResult(
        tickets: Set<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int>

    fun calculateReturnRate(purchaseAmount: Int): Double
}

class WinningStatisticsImpl : WinningStatistics {
    private val results = mutableMapOf<Rank, Int>()

    override fun calculateResult(
        tickets: Set<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        tickets.forEach { ticket ->
            val countOfMatch = ticket.matchCount(winningNumbers)
            val matchBonus = ticket.containsBonus(bonusNumber)
            val rank = Rank.valueOf(countOfMatch, matchBonus)
            if (rank != Rank.MISS) {
                results[rank] = results.getOrDefault(rank, 0) + 1
            }
        }
        return results.toMap()
    }

    override fun calculateReturnRate(purchaseAmount: Int): Double {
        val totalPrize =
            results
                .entries
                .sumOf { (rank, count) ->
                    rank
                        .winningMoney * count
                }
        return (totalPrize.toDouble() / purchaseAmount)
    }
}
