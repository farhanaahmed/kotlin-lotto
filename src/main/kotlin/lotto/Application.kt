package lotto

import lotto.controller.LottoController
import lotto.model.LottoTicketGenerator
import lotto.model.RandomNumbersGeneratorWrapperImpl
import lotto.model.WinningStatistics
import lotto.view.InputValidator
import lotto.view.InputView
import lotto.view.ResultView

fun main() {
    val lottoController =
        LottoController(
            inputView = InputView(),
            resultView = ResultView(),
            lottoTicketGenerator =
                LottoTicketGenerator(
                    randomNumbersGeneratorWrapper = RandomNumbersGeneratorWrapperImpl(),
                ),
            inputValidator = InputValidator(),
            winningStatistics = WinningStatistics(),
        )
    lottoController.run()
}
