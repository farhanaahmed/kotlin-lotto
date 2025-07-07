package lotto.model

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import kotlin.test.Test

class WinningStatisticsImplTest {
    private val winningStatisticsImplTest: WinningStatistics = WinningStatisticsImpl()

    @Test
    fun `calculateResult returns correct rank counts`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val tickets =
            setOf<Lotto>(
                LottoImpl(listOf(1, 2, 3, 4, 5, 6)),
                LottoImpl(listOf(1, 2, 3, 4, 5, 7)),
                LottoImpl(listOf(1, 2, 3, 4, 5, 8)),
                LottoImpl(listOf(1, 2, 3, 4, 8, 9)),
                LottoImpl(listOf(1, 2, 3, 8, 9, 10)),
                LottoImpl(listOf(1, 2, 8, 9, 10, 11)),
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
    fun `returns correct profit rate`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val tickets =
            setOf<Lotto>(
                LottoImpl(listOf(1, 2, 3, 4, 5, 6)),
                LottoImpl(listOf(1, 2, 3, 4, 5, 7)),
            )
        winningStatisticsImplTest.calculateResult(tickets, winningNumbers, bonusNumber)
        val purchaseAmount = 2000
        val expectedTotalPrize = Rank.FIRST.winningMoney + Rank.SECOND.winningMoney
        val expectedProfitRate = (expectedTotalPrize.toDouble() / purchaseAmount)

        val profitRate = winningStatisticsImplTest.calculateReturnRate(purchaseAmount)

        assertThat(profitRate).isEqualTo(expectedProfitRate)
    }
}
