package lotto.controller

import lotto.model.InputValidatorImpl
import lotto.model.Lotto
import lotto.model.LottoTicketGenerator
import lotto.model.WinningStatistics
import lotto.view.InputView
import lotto.view.ResultView

interface LottoController {
    fun run()
}

class LottoControllerImpl(
    private val inputView: InputView,
    private val inputValidatorImpl: InputValidatorImpl,
    private val resultView: ResultView,
    private val lottoTicketGenerator: LottoTicketGenerator,
    private val winningStatistics: WinningStatistics,
) : LottoController {
    private fun processTickets(purchaseAmount: Int): Set<Lotto> {
        val numberOfTickets = lottoTicketGenerator.generateNumberOfTickets(purchaseAmount)
        resultView.printNumberOfTickets(numberOfTickets)
        val numberOfManualTickets = inputView.readManualNumberOfTickets()
        val manualTickets = inputView.readManualTickets(numberOfManualTickets)
        val tickets =
            lottoTicketGenerator
                .generateTickets(
                    manualTickets,
                    numberOfTickets,
                )
        resultView.printTickets(tickets)
        return tickets
    }

    private fun processResult(
        tickets: Set<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ) {
        val result = winningStatistics.calculateResult(tickets, winningNumbers, bonusNumber)
        resultView.printStatistics(result)
    }

    private fun processReturnRate(purchaseAmount: Int) {
        val returnRate = winningStatistics.calculateReturnRate(purchaseAmount)
        resultView.printReturnRate(returnRate)
    }

    override fun run() {
        val purchaseAmount = inputView.readPurchaseAmount()
        inputValidatorImpl.validatePurchaseAmount(purchaseAmount)
        val numberOfManualTicket = inputView.readManualNumberOfTickets()
        val manualTickets = inputView.readManualTickets(numberOfManualTicket)
        val tickets = processTickets(purchaseAmount)
        val winningNumbers = inputView.readWinningNumbers()
        inputValidatorImpl.validateWinningNumbers(winningNumbers)
        val bonusNumber = inputView.readBonusNumber()
        inputValidatorImpl.validateBonusNumber(bonusNumber, winningNumbers)
        processResult(tickets, winningNumbers, bonusNumber)
        processReturnRate(purchaseAmount)
    }
}
