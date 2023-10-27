package baseball

import baseball.constants.GameConfig
import baseball.model.Answer
import baseball.model.BaseballInput
import baseball.model.MenuInput
import baseball.view.View

class BaseballGame() {
    fun play() {
        var isStay = true
        var answer = Answer()
        val view = View()
        val controller = Controller()
        val baseballModel = BaseballInput()
        val menuModel = MenuInput()

        view.gameStartPrompt()
        while (isStay) {
            controller.inputBaseballWithValidator(view, baseballModel)
            val isAllStrike = controller.calculateBaseball(view, baseballModel, answer)
            if (isAllStrike.not()) {
                continue
            }
            view.gameEndPrompt()

            controller.inputMenuWithValidator(view, menuModel)
            when (menuModel.selectedMenu) {
                GameConfig.MENU_RANGE_FIRST -> answer = Answer()
                GameConfig.MENU_RANGE_LAST -> isStay = false
            }
        }
    }
}

fun main() {
    val game = BaseballGame()
    game.play()
}