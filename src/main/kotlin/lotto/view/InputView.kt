package lotto.view

class InputView {
    fun readPurchaseAmount(): Int {
        println("Please enter the purchase amount.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Purchase amount must be a number.")
        }
    }

    fun readManualNumberOfTickets(): Int {
        println("Enter the number of manual tickets to purchase.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("The number of manual tickets must be a number.")
        }
    }

    fun readManualTickets(numberOfManualTickets: Int): List<List<Int>> {
        println("Enter the numbers for manual tickets.")
        val manualTickets = mutableListOf<List<Int>>()

        repeat(numberOfManualTickets) {
            try {
                val ticket =
                    readlnOrNull()
                        ?.split(",")
                        ?.map {
                            it.trim().toInt()
                        } ?: throw IllegalArgumentException("Input cannot be empty.")

                manualTickets.add(ticket)
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("Manual tickets must contain only integer numbers separated by commas.")
            }
        }

        return manualTickets
    }

    fun readWinningNumbers(): List<Int> {
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

    fun readBonusNumber(): Int {
        println("Please enter the bonus number.")
        return try {
            readln().toIntOrNull() ?: throw IllegalArgumentException("Input can not be empty.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Bonus number must be a number.")
        }
    }
}
