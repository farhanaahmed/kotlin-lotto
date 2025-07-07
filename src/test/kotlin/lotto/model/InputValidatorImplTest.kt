package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class InputValidatorImplTest {
    private val inputValidator: InputValidator = InputValidatorImpl()

    @Test
    fun `throws an exception for purchase amount less than 1000`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validatePurchaseAmount(500)
            }
        assertThat(exception.message)
            .isEqualTo(
                "Purchase amount must be an integer number greater than or equal 1000 and divisible by 1000.",
            )
    }

    @Test
    fun `throws an exception for purchase amount not divisible by 1000`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validatePurchaseAmount(1500)
            }
        assertThat(exception.message)
            .isEqualTo(
                "Purchase amount must be an integer number greater than or equal 1000 and divisible by 1000.",
            )
    }

    @Test
    fun `doesn't throw an exception for valid number of manual tickets`() {
        val tickets =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18),
            )

        assertDoesNotThrow {
            inputValidator.validateManualTickets(3, tickets)
        }
    }

    @Test
    fun `throws an exception when a ticket does not have exactly 6 numbers`() {
        val tickets =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 18, 20),
            )

        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator.validateManualTickets(3, tickets)
            }

        assertEquals("Manual ticket must contain exactly 6 integer numbers separated by comma.", exception.message)
    }

    @Test
    fun `throws an exception when numbers are out of range`() {
        val tickets =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
                listOf(13, 14, 15, 16, 17, 90),
            )

        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator.validateManualTickets(3, tickets)
            }

        assertEquals("Manual ticket's numbers must be between 1 and 45.", exception.message)
    }

    @Test
    fun `throws an exception for winning numbers not exactly six numbers`() {
        val lessNumberException =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateWinningNumbers(listOf(1, 2, 3, 4, 5))
            }
        assertThat(lessNumberException.message)
            .isEqualTo(
                "Winning numbers must contain exactly 6 integer numbers separated by comma.",
            )

        val moreNumberException =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateWinningNumbers(listOf(1, 2, 3, 4, 5, 6, 7))
            }
        assertThat(moreNumberException.message)
            .isEqualTo(
                "Winning numbers must contain exactly 6 integer numbers separated by comma.",
            )
    }

    @Test
    fun `throws an exception for winning numbers not between 1 and 45`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateWinningNumbers(listOf(0, 2, 3, 4, 5, 4))
            }
        assertThat(exception.message).isEqualTo("Winning numbers must be between 1 and 45.")
    }

    @Test
    fun `throws an exception for duplicate winning numbers`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateWinningNumbers(listOf(1, 2, 2, 4, 5, 6))
            }
        assertThat(exception.message).isEqualTo("Winning numbers must be unique.")
    }

    @Test
    fun `throws an exception for bonus number not between 1 and 45`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateBonusNumber(0, listOf(1, 2, 3, 4, 5, 6))
            }
        assertThat(exception.message).isEqualTo("Bonus number must be a positive integer between 1 and 45.")
    }

    @Test
    fun `throws an exception if bonus number is in winning numbers`() {
        val exception =
            assertThrows<IllegalArgumentException> {
                inputValidator
                    .validateBonusNumber(4, listOf(1, 2, 3, 4, 5, 6))
            }
        assertThat(exception.message).isEqualTo("Bonus number must be distinct from winning numbers.")
    }
}
