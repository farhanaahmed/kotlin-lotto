package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTicketGeneratorTest {
    private val randomNumbersGeneratorWrapper =
        object :
            RandomNumbersGeneratorWrapper {
            override fun generateRandomNumbers(): List<Int> {
                return listOf(1, 2, 3, 4, 5, 6)
            }
        }

    private val lottoTicketGenerator: LottoTicketGenerator = LottoTicketGenerator(randomNumbersGeneratorWrapper)

    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 15000])
    fun `generate the correct number of tickets`(purchaseAmount: Int) {
        val numberOfManualTickets = 3
        val rightNumberOfTickets = (purchaseAmount - (numberOfManualTickets * 1000)) / 1000

        val numberOfTickets =
            lottoTicketGenerator
                .generateNumberOfTickets(
                    purchaseAmount,
                    numberOfManualTickets,
                )
        assertThat(numberOfTickets).isEqualTo(rightNumberOfTickets)
    }

    @Test
    fun `generates tickets with 6 numbers each`() {
        val tickets =
            listOf(
                ManualTicket(
                    listOf(1, 2, 3, 4, 5, 6),
                ),
                ManualTicket(
                    listOf(7, 8, 9, 10, 11, 12),
                ),
                ManualTicket(
                    listOf(13, 14, 15, 16, 17, 18),
                ),
            )
        val numberOfTickets = 3

        val result = lottoTicketGenerator.generateTickets(tickets, numberOfTickets)

        assertThat(result).allSatisfy {
            assertThat(it.getTickets()).hasSize(6)
        }
    }

    @Test
    fun `generates tickets with numbers between 1 and 45`() {
        val tickets =
            listOf(
                ManualTicket(
                    listOf(1, 2, 3, 4, 5, 6),
                ),
                ManualTicket(
                    listOf(7, 8, 9, 10, 11, 12),
                ),
                ManualTicket(
                    listOf(13, 14, 15, 16, 17, 18),
                ),
            )
        val numberOfTickets = 2

        val result = lottoTicketGenerator.generateTickets(tickets, numberOfTickets)

        assertThat(result).allSatisfy {
            it.getTickets().forEach { number ->
                assertThat(number).isBetween(1, 45)
            }
        }
    }

    @Test
    fun `throws an exception if ticket has more than 6 numbers`() {
        val tickets =
            listOf(
                ManualTicket(
                    listOf(1, 2, 3, 4, 5, 6),
                ),
                ManualTicket(
                    listOf(7, 8, 9, 10, 11, 12),
                ),
                ManualTicket(
                    listOf(13, 14, 15, 16, 17, 18),
                ),
            )
        val faultyRandomNumbersGenerator =
            object :
                RandomNumbersGeneratorWrapper {
                override fun generateRandomNumbers(): List<Int> {
                    return listOf(1, 2, 3, 4, 5)
                }
            }
        val faultyLottoTicketGenerator =
            LottoTicketGenerator(faultyRandomNumbersGenerator)
        assertThrows<IllegalArgumentException> {
            faultyLottoTicketGenerator.generateTickets(tickets, 1)
        }
    }

    @Test
    fun `throws an exception if ticket has less than 6 numbers`() {
        val tickets =
            listOf(
                ManualTicket(
                    listOf(1, 2, 3, 4, 5, 6),
                ),
                ManualTicket(
                    listOf(7, 8, 9, 10, 11, 12),
                ),
                ManualTicket(
                    listOf(13, 14, 15, 16, 17, 18),
                ),
            )
        val faultyRandomNumbersGenerator =
            object :
                RandomNumbersGeneratorWrapper {
                override fun generateRandomNumbers(): List<Int> {
                    return listOf(1, 2, 3, 4, 5)
                }
            }

        val faultyLottoTicketGenerator = LottoTicketGenerator(faultyRandomNumbersGenerator)

        assertThrows<IllegalArgumentException> {
            faultyLottoTicketGenerator.generateTickets(tickets, 1)
        }
    }
}
