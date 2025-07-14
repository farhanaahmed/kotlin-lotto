package lotto.model

class Lotto(private val numbers: List<Int>) {
    private val sortedNumbers: List<Int>

    init {
        require(numbers.size == REQUIRED_COUNT) { "Lotto must contain exactly 6 numbers." }
        require(numbers.distinct().size == REQUIRED_COUNT) { "Lotto numbers must be unique." }
        require(numbers.all { it in LOWER_RANGE..UPPER_RANGE }) { "Lotto numbers must be between 1 and 45." }
        sortedNumbers = numbers.sorted()
    }

    fun matchCount(winningNumbers: WinningNumbers): Int {
        return numbers.count { it in winningNumbers.toList() }
    }

    fun containsBonus(bonusNumber: Int): Boolean {
        return bonusNumber in numbers
    }

    fun getTickets(): List<Int> {
        return sortedNumbers
    }

    override fun toString(): String {
        return sortedNumbers.toString()
    }

    companion object {
        private const val REQUIRED_COUNT = 6
        private const val LOWER_RANGE = 1
        private const val UPPER_RANGE = 45
    }
}
