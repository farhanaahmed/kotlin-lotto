package lotto.model

data class WinningNumbers(private val winningNumbers: List<Int>) {
    fun toList(): List<Int> = winningNumbers
}
