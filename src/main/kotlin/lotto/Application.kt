package lotto

import lotto.controller.LottoControllerImpl
import lotto.model.InputValidatorImpl
import lotto.model.LottoTicketGeneratorImpl
import lotto.model.RandomNumbersGeneratorWrapperImpl
import lotto.model.WinningStatisticsImpl
import lotto.view.InputViewImpl
import lotto.view.ResultViewImpl

fun main() {
    val lottoController =
        LottoControllerImpl(
            inputView = InputViewImpl(),
            resultView = ResultViewImpl(),
            lottoTicketGenerator =
                LottoTicketGeneratorImpl(
                    randomNumbersGeneratorWrapper = RandomNumbersGeneratorWrapperImpl(),
                ),
            inputValidatorImpl = InputValidatorImpl(),
            winningStatistics = WinningStatisticsImpl(),
        )
    lottoController.run()
}
