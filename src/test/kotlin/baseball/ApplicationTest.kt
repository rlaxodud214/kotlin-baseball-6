package baseball

import baseball.model.BaseballInput
import baseball.model.MenuInput
import camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
    var menuInput = MenuInput()
    var baseballInput = BaseballInput()
    @Test
    fun `야구게임의 모든 출력 검증, 게임 종료후 재시작 검증`() {
        assertRandomNumberInRangeTest(
            {
                run("456", "231", "132", "123", "1", "123", "498", "489", "2")
                assertThat(output())
                    .contains(
                        "낫싱", "3볼", "2볼 1스트라이크", "3스트라이크",
                        "낫싱", "2볼 1스트라이크", "3스트라이크", "게임 종료",
                    )
            },
            1, 2, 3, 4, 8, 9
        )

        assertRandomNumberInRangeTest(
            {
                run("246", "135", "1", "597", "589", "2")
                assertThat(output())
                    .contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료")
            },
            1, 3, 5, 5, 8, 9
        )
    }

    @Test
    fun `사용자 Baseball 입력 예외 테스트`() {
        val TestExceptionData: List<String> =
            listOf(
                "12", "1234", "012", "01", "1",  // 자리수, 숫자 0
                "a165", "#^\$", "&", "$", "+",   // 특수 문자
                "112", "565", "988", "0945",     // 숫자 중복
            )
        TestExceptionData.forEach {
            assertThrows<IllegalArgumentException> {
                println("오류 체크 : $it")
                baseballInput.setDataWithValidation(it)
            }
        }
    }

    @Test
    fun `사용자 Menu 입력 예외 테스트`() {
        val TestExceptionData: List<String> =
            listOf(
                "a", "%", "12", "21", "01",
                "3", "03", "22", "123", "10",
            )

        TestExceptionData.forEach { it ->
            assertThrows<IllegalArgumentException> {
                menuInput.setDataWithValidation(it)
            }
        }
    }

    override fun runMain() {
        main()
    }
}