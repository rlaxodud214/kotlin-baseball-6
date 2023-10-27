package baseball

import baseball.constants.GameConfig
import baseball.model.Answer
import baseball.model.BaseballInput
import baseball.model.MenuInput

class BaseballGame(
    private val answer: Answer,
    private val baseballInput: BaseballInput,
    private val menuInput: MenuInput,
) {
    fun play() {
        var isStay = true
        View.gameStartPrompt()

        while (isStay) {
            // [2]. baseball / 입력, 검증
            Controller.inputBaseballWithValidator(baseballInput)
            // [3]. baseball / 계산, 결과 출력
            val isAllStrike = Controller.calculateBaseball(baseballInput, answer)
            when (isAllStrike) {
                true -> View.gameEndPrompt()
                false -> continue
            }

            // [4]. menu / 입력, 검증 및 처리
            Controller.inputMenuWithValidator(menuInput)
            when (menuInput.selectedMenu) {
                GameConfig.MENU_RANGE_FIRST -> answer.reset()
                GameConfig.MENU_RANGE_LAST -> isStay = false
            }
        }
    }
}

fun main() {
    val game = BaseballGame(
        Answer(),
        BaseballInput(),
        MenuInput()
    )
    game.play()
}