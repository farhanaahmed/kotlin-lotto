package lotto.view

import lotto.model.ManualTicket
import lotto.model.WinningNumbers

class InputValidator {
    fun validatePurchaseAmount(purchaseAmount: Int) {
        require(purchaseAmount >= DIVISOR && purchaseAmount % DIVISOR == 0) {
            "Purchase amount must be an integer number greater than or equal 1000 and divisible by 1000."
        }
    }

    fun validateManualTicketAmount(
        purchaseAmount: Int,
        numberOfManualTickets: Int,
    ) {
        require(purchaseAmount >= numberOfManualTickets * 1000) {
            "Not enough money to buy $numberOfManualTickets manual tickets with $purchaseAmount KRW."
        }
    }

    fun validateManualTickets(manualTickets: List<ManualTicket>) {
        manualTickets.forEach { ticket ->
            require(ticket.toList().size == REQUIRED_COUNT) {
                "Manual ticket must contain exactly 6 integer numbers separated by comma."
            }
            require(ticket.toList().all { it in LOWER_RANGE..UPPER_RANGE }) {
                "Manual ticket's numbers must be between 1 and 45."
            }
        }
    }

    fun validateWinningNumbers(winningNumbers: WinningNumbers) {
        require(winningNumbers.toList().size == REQUIRED_COUNT) {
            "Winning numbers must contain exactly 6 integer numbers separated by comma."
        }
        require(winningNumbers.toList().all { it in LOWER_RANGE..UPPER_RANGE }) {
            "Winning numbers must be between 1 and 45."
        }
        require(winningNumbers.toList().distinct().size == REQUIRED_COUNT) {
            "Winning numbers must be unique."
        }
    }

    fun validateBonusNumber(
        bonusNumber: Int,
        winningNumbers: WinningNumbers,
    ) {
        require(bonusNumber in LOWER_RANGE..UPPER_RANGE) {
            "Bonus number must be a positive integer between 1 and 45."
        }
        require(bonusNumber !in winningNumbers.toList()) {
            "Bonus number must be distinct from winning numbers."
        }
    }

    companion object {
        private const val DIVISOR = 1000
        private const val LOWER_RANGE = 1
        private const val UPPER_RANGE = 45
        private const val REQUIRED_COUNT = 6
    }
}
