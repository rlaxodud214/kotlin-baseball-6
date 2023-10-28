package baseball.view

import baseball.constants.GameConfig
import camp.nextstep.edu.missionutils.Console

class View() {
    companion object {
        const val GAME_START = "숫자 야구 게임을 시작합니다."
        const val GAME_END = "${GameConfig.BASEBALL_DIGITS}개의 숫자를 모두 맞히셨습니다! 게임 종료"

        const val INPUT_BASEBALL = "숫자를 입력해주세요 : "
        const val INPUT_MENU = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."

        const val RESULT_NOTHING = "낫싱"
        const val RESULT_ALL_STRIKE = "${GameConfig.BASEBALL_DIGITS}스트라이크"
    }
    fun gameStartPrompt() = println(GAME_START)
    fun gameEndPrompt() = println(GAME_END)

    /** [2, 4]. baseball, menu / 입력, 검증
     * 1. 입력을 위한 안내 문구 출력 (View) */
    fun printInputPrompt(digit: Int): String {
        when (digit) {
            GameConfig.BASEBALL_DIGITS -> {
                print(INPUT_BASEBALL)
                return Console.readLine()
            }
            GameConfig.MENU_DIGITS -> {
                println(INPUT_MENU)
                return Console.readLine()
            }
        }
        throw Exception("입력 시 오류 발생")
    }

    /** [3]. baseball / 계산, 결과 출력
     * 2. Ball, Strike 계산 결과 출력 (View) */
    fun printCalculateResult(ball: Int, strike: Int) {
        when {
            strike == GameConfig.BASEBALL_DIGITS -> println(RESULT_ALL_STRIKE)
            strike == 0 && ball == 0 -> println(RESULT_NOTHING)
            strike != 0 || ball != 0 -> {
                var calculateResult = "$ball${GameConfig.BALL}".takeIf { ball > 0 } ?: ""
                calculateResult += " $strike${GameConfig.STRIKE}".takeIf { strike > 0 } ?: ""
                println(calculateResult.trim()) // 스트라이크만 출력 시 앞에 공백 제거
            }
        }
    }
}