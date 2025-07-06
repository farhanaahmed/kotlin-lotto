package lotto.model

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class RankTest {
    @Test
    fun `returns FIRST when matchCount is 6`() {
        val rank = Rank.valueOf(6, matchBonus = false)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `returns SECOND when matchCount is 5 and bonus number is matched`() {
        val rank = Rank.valueOf(5, matchBonus = true)

        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `returns THIRD when matchCount is 5 and bonus number is not matched`() {
        val rank = Rank.valueOf(5, matchBonus = false)

        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `returns FOURTH when matchCount is 4`() {
        val rank = Rank.valueOf(4, matchBonus = false)

        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `returns FIFTH when matchCount is 3`() {
        val rank = Rank.valueOf(3, matchBonus = false)

        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `returns MISS when matchCount is less than 3`() {
        val rankWithTwoMatches = Rank.valueOf(2, matchBonus = false)
        val rankWithZeroMatches = Rank.valueOf(0, matchBonus = false)

        assertThat(rankWithTwoMatches).isEqualTo(Rank.MISS)
        assertThat(rankWithZeroMatches).isEqualTo(Rank.MISS)
    }
}
