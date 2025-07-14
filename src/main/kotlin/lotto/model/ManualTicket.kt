package lotto.model

data class ManualTicket(private val tickets: List<Int>) {
    fun toList(): List<Int> = tickets
}
