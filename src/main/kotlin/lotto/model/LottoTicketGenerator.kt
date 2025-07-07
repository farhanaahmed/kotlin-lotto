package lotto.model

interface LottoTicketGenerator {
    fun generateNumberOfTickets(purchaseAmount: Int): Int

    fun generateTickets(numberOfTickets: Int): Set<Lotto>

    fun generateRandomNumbers(): List<Int>
}

class LottoTicketGeneratorImpl(private val randomNumbersGeneratorWrapper: RandomNumbersGeneratorWrapper) :
    LottoTicketGenerator {
    override fun generateNumberOfTickets(purchaseAmount: Int): Int {
        val numberOfTickets = purchaseAmount / DIVISOR
        return numberOfTickets
    }

    override fun generateTickets(numberOfTickets: Int): Set<Lotto> {
        val tickets = mutableSetOf<Lotto>()
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
