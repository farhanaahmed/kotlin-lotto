package lotto.model

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import kotlin.test.Test

class WinningStatisticsTest {
    private val winningStatisticsImplTest: WinningStatistics = WinningStatistics()

    @Test
    fun `calculateResult returns correct rank counts`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val tickets =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 7)),
                Lotto(listOf(1, 2, 3, 4, 5, 8)),
                Lotto(listOf(1, 2, 3, 4, 8, 9)),
                Lotto(listOf(1, 2, 3, 8, 9, 10)),
                Lotto(listOf(1, 2, 8, 9, 10, 11)),
            )

        val result = winningStatisticsImplTest.calculateResult(tickets, winningNumbers, bonusNumber)

        assertThat(result[Rank.FIRST]).isEqualTo(1)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
        assertThat(result[Rank.FOURTH]).isEqualTo(1)
        assertThat(result[Rank.FIFTH]).isEqualTo(1)
        assertThat(result[Rank.MISS]).isNull()
    }

    @Test
    fun `returns correct return rate`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val tickets =
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 2, 3, 4, 5, 7)),
            )
        winningStatisticsImplTest.calculateResult(tickets, winningNumbers, bonusNumber)
        val purchaseAmount = 2000
        val expectedTotalPrize = Rank.FIRST.winningMoney + Rank.SECOND.winningMoney
        val expectedReturnRate = (expectedTotalPrize.toDouble() / purchaseAmount)

        val profitRate = winningStatisticsImplTest.calculateReturnRate(purchaseAmount)

        assertThat(profitRate).isEqualTo(expectedReturnRate)
    }
}
