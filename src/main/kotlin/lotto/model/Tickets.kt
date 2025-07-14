package lotto.model

data class Tickets(private val tickets: List<Lotto>) {
    fun toList(): List<Lotto> = tickets
}
