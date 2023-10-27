package baseball

import baseball.constants.GameConfig
import baseball.model.Answer
import baseball.model.BaseballInput
import baseball.model.MenuInput
import baseball.view.View

class Controller {
    /** [2, 4]. baseball, menu / 입력, 검증
     * 1. 안내 문구 call, 사용자 입력
     * 2. 사용자 입력 검증 call (Controller) */
    fun inputBaseballWithValidator(view: View, baseballModel: BaseballInput) {
        val userInputData = view.printInputPrompt(GameConfig.BASEBALL_DIGITS)
        baseballModel.setDataWithValidation(userInputData)
    }

    fun inputMenuWithValidator(view: View, menuModel: MenuInput) {
        val userInputData = view.printInputPrompt(GameConfig.MENU_DIGITS)
        menuModel.setDataWithValidation(userInputData)
    }

    /** [3]. baseball / 계산, 결과 출력
     * 1. Ball, Strike 계산 함수 call
     * 2. 계산 결과 출력 함수 call (Controller) */
    fun calculateBaseball(view: View, baseballModel: BaseballInput, answer: Answer): Boolean {
        val (ball, strike) = baseballModel.countBaseball(answer.number) // Controller -> Model
        view.printCalculateResult(ball, strike)

        val isAllStrike = (strike == GameConfig.BASEBALL_DIGITS)
        return isAllStrike
    }
}