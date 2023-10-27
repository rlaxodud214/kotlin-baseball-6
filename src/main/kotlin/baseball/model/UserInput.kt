package baseball.model

import baseball.constants.GameConfig
import baseball.utils.charToInt
import camp.nextstep.edu.missionutils.Console

open class UserInput(
    private val digit: Int,
    private val range: CharRange,
) {

    /** [2, 4]. baseball, menu / 입력, 검증
     * 2.A) 입력된 문자열의 길이 검증
     * 2.B) 입력된 문자열의 범위 검증 (Model) */
    open fun setDataWithValidation(userInputData: String) {
        if (userInputData.length != digit) {
            throw IllegalArgumentException("입력된 값($userInputData)은 ${digit}자리가 아닙니다.")
        }

        // 입력된 데이터 모두가 range에 속하는지 체크
        val rangeCheck = userInputData.map { it }.all { it in range }
        if (rangeCheck == false) {
            throw IllegalArgumentException("입력된 값($userInputData) 중에 ${range.first}~${range.last} 범위를 벗어난 문자가 있습니다.")
        }
    }
}

/** Baseball 객체에만 필요한 변수 및 메소드 구분 */
class BaseballInput : UserInput(
    GameConfig.BASEBALL_DIGITS,
    GameConfig.BASEBALL_RANGE,
) {
    private var _inputDataArray: List<Int> = listOf()

    val inputDataArray: List<Int> get() = _inputDataArray

    override fun setDataWithValidation(userInputData: String) {
        super.setDataWithValidation(userInputData)
        // 숫자 중복 검사 추가 수행
        checkDuplicate(userInputData, GameConfig.BASEBALL_DIGITS)
        // 검증 이후 데이터 셋
        _inputDataArray = userInputData.map { it.charToInt() }
    }

    /** [2]. baseball / 입력, 검증
     * 2.C) 숫자 중복 체크 (Model) */
    private fun checkDuplicate(userInputData: String, digit: Int) {
        val inputDataSet = userInputData.map {
            it.charToInt()
        }.toSet()

        if (inputDataSet.size < digit) {
            throw IllegalArgumentException("입력된 숫자($userInputData) 중에 중복이 있습니다.")
        }
    }

    data class BallStrike(val ball: Int, val strike: Int) // data class 하나로 코드가 굉장히 깔끔해졌다!!!

    /** [3]. baseball / 계산, 결과 출력
     * 1. Ball, Strike 계산 (Model) */
    fun countBaseball(answer: List<Int>): BallStrike {
        val calculateResult = BallStrike(
            countBall(answer),
            countStrike(answer)
        )
        return calculateResult
    }

    private fun countBall(answer: List<Int>): Int {
        return inputDataArray.filterIndexed { index, i ->
            i in answer.filter { it != answer[index] } // Ball
        }.size
    }

    private fun countStrike(answer: List<Int>): Int {
        return inputDataArray.filterIndexed { index, i ->
            i == answer[index] // Strike
        }.size
    }
}

/** Menu 객체에만 필요한 변수 및 메소드 구분 */
class MenuInput : UserInput(
    GameConfig.MENU_DIGITS,
    GameConfig.MENU_RANGE,
) {
    private var _selectedMenu: Int = -1
    val selectedMenu: Int get() = _selectedMenu

    override fun setDataWithValidation(userInputData: String) {
        super.setDataWithValidation(userInputData)
        // 검증 이후 데이터 셋
        _selectedMenu = userInputData.toInt()
    }
}