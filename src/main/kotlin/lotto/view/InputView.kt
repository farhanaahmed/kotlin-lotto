package lotto.view

interface InputView {
    fun readPurchaseAmount(): Int

    fun readManualNumberOfTickets(): Int

    fun readManualTickets(numberOfManualTickets: Int): Set<Set<Int>>

    fun readWinningNumbers(): List<Int>

    fun readBonusNumber(): Int
}

class InputViewImpl : InputView {
    override fun readPurchaseAmount(): Int {
        println("Please enter the purchase amount.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Purchase amount must be a number.")
        }
    }

    override fun readManualNumberOfTickets(): Int {
        println("Enter the number of manual tickets to purchase.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("The number of manual tickets must be a number.")
        }
    }

    override fun readManualTickets(numberOfManualTickets: Int): Set<Set<Int>> {
        println("Enter the numbers for manual tickets.")
        val manualTickets = mutableSetOf<Set<Int>>()

        repeat(numberOfManualTickets) {
            try {
                val ticket =
                    readlnOrNull()
                        ?.split(",")
                        ?.map {
                            it.trim().toInt()
                        } ?: throw IllegalArgumentException("Input cannot be empty.")

                manualTickets.add(ticket.toSet())
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Manual tickets must contain only integer numbers separated by commas.")
            }
        }

        return manualTickets
    }

    override fun readWinningNumbers(): List<Int> {
        println("Please enter last week’s winning numbers.")
        return try {
            readlnOrNull()
                ?.split(",")
                ?.map {
                    it.toInt()
                } ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Winning numbers must contain only integer numbers separated by comma.")
        }
    }

    override fun readBonusNumber(): Int {
        println("Please enter the bonus number.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Bonus number must be a number.")
        }
    }
}
