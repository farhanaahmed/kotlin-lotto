package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTicketGeneratorImplTest {
    private val randomNumbersGeneratorWrapper =
        object :
            RandomNumbersGeneratorWrapper {
            override fun generateRandomNumbers(): List<Int> {
                return listOf(1, 2, 3, 4, 5, 6)
            }
        }

    private val lottoTicketGenerator: LottoTicketGenerator = LottoTicketGeneratorImpl(randomNumbersGeneratorWrapper)

    @ParameterizedTest
    @ValueSource(ints = [1000, 2000, 3000, 4000, 15000])
    fun `generate the correct number of tickets`(purchaseAmounts: Int) {
        val rightNumberOfTickets = purchaseAmounts / 1000
        val numberOfTickets = lottoTicketGenerator.generateNumberOfTickets(purchaseAmounts)
        assertThat(rightNumberOfTickets).isEqualTo(numberOfTickets)
    }

    @Test
    fun `generates tickets with 6 numbers each`() {
        val numberOfTickets = 3

        val result = lottoTicketGenerator.generateTickets(numberOfTickets)

        assertThat(result).allSatisfy {
            assertThat(it.getTickets()).hasSize(6)
        }
    }

    @Test
    fun `generates tickets with numbers between 1 and 45`() {
        val numberOfTickets = 2

        val result = lottoTicketGenerator.generateTickets(numberOfTickets)

        assertThat(result).allSatisfy {
            it.getTickets().forEach { number ->
                assertThat(number).isBetween(1, 45)
            }
        }
    }

    @Test
    fun `throws an exception if ticket has more than 6 numbers`() {
        val faultyRandomNumbersGenerator =
            object :
                RandomNumbersGeneratorWrapper {
                override fun generateRandomNumbers(): List<Int> {
                    return listOf(1, 2, 3, 4, 5, 6, 7)
                }
            }

        val faultyLottoTicketGenerator: LottoTicketGenerator = LottoTicketGeneratorImpl(faultyRandomNumbersGenerator)

        assertThrows<IllegalArgumentException> {
            faultyLottoTicketGenerator.generateTickets(1)
        }
    }

    @Test
    fun `throws an exception if ticket has less than 6 numbers`() {
        val faultyRandomNumbersGenerator =
            object :
                RandomNumbersGeneratorWrapper {
                override fun generateRandomNumbers(): List<Int> {
                    return listOf(1, 2, 3, 4, 5)
                }
            }

        val faultyLottoTicketGenerator: LottoTicketGenerator = LottoTicketGeneratorImpl(faultyRandomNumbersGenerator)

        assertThrows<IllegalArgumentException> {
            faultyLottoTicketGenerator.generateTickets(1)
        }
    }
}
