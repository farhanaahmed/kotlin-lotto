package lotto.model

interface LottoTicketGenerator {
    fun generateNumberOfTickets(
        purchaseAmount: Int,
        numberOfManualTickets: Int,
    ): Int

    fun generateTickets(
        manualTickets: List<List<Int>>,
        numberOfTickets: Int,
    ): List<Lotto>

    fun generateRandomNumbers(): List<Int>
}

class LottoTicketGeneratorImpl(private val randomNumbersGeneratorWrapper: RandomNumbersGeneratorWrapper) :
    LottoTicketGenerator {
    override fun generateNumberOfTickets(
        purchaseAmount: Int,
        numberOfManualTickets: Int,
    ): Int {
        val numberOfTickets = (purchaseAmount - (numberOfManualTickets * 1000)) / DIVISOR
        return numberOfTickets
    }

    override fun generateTickets(
        manualTickets: List<List<Int>>,
        numberOfTickets: Int,
    ): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        tickets.addAll(manualTickets.map { LottoImpl(it.sorted()) })
        repeat(numberOfTickets) {
            val singleTicket = generateRandomNumbers().sorted()
            val lottoTicket = LottoImpl(singleTicket)
            tickets.add(lottoTicket)
        }
        return tickets
    }

    override fun generateRandomNumbers(): List<Int> {
        return randomNumbersGeneratorWrapper.generateRandomNumbers()
    }

    companion object {
        private const val DIVISOR = 1000
    }
}
