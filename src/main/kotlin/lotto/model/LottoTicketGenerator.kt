package lotto.model

class LottoTicketGenerator(private val randomNumbersGeneratorWrapper: RandomNumbersGeneratorWrapper) {
    fun generateNumberOfTickets(
        purchaseAmount: Int,
        numberOfManualTickets: Int,
    ): Int {
        val numberOfTickets = (purchaseAmount - (numberOfManualTickets * 1000)) / DIVISOR
        return numberOfTickets
    }

    fun generateTickets(
        manualTickets: List<ManualTicket>,
        numberOfTickets: Int,
    ): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        tickets.addAll(manualTickets.map { Lotto(it.toList().sorted()) })
        repeat(numberOfTickets) {
            val singleTicket = generateRandomNumbers().sorted()
            val lottoTicket = Lotto(singleTicket)
            tickets.add(lottoTicket)
        }
        return tickets
    }

    private fun generateRandomNumbers(): List<Int> {
        return randomNumbersGeneratorWrapper.generateRandomNumbers()
    }

    companion object {
        private const val DIVISOR = 1000
    }
}
