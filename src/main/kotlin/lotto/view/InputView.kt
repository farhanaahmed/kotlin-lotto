package lotto.view

interface InputView {
    fun readPurchaseAmount(): Int

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
