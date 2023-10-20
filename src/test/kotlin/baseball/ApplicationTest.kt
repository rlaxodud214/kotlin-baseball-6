package baseball

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest : NsTest() {
//    @Test
//    fun `게임종료 후 재시작`() {
//        assertRandomNumberInRangeTest(
//            {
//                run("246", "135", "1", "597", "589", "2")
//                assertThat(output())
//                    .contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료")
//            },
//            1, 3, 5, 5, 8, 9
//        )
//    }

    @Test
    fun `입력 예외 테스트`() {
        assertSimpleTest {
            // 자리수 테스트
            assertThrows<IllegalArgumentException> { runException("1234") } // 4자리
            assertThrows<IllegalArgumentException> { runException("12") }   // 2자리
            // 숫자0, 문자, 특수문자 테스트
            assertThrows<IllegalArgumentException> { runException("012") }  // 숫자0 포함 3자리
            assertThrows<IllegalArgumentException> { runException("aaa") }  // 문자 3자리
            assertThrows<IllegalArgumentException> { runException("a165") } // 문자 포함 4자리
            assertThrows<IllegalArgumentException> { runException("#^$") }  // 특수문자 3자리
            assertThrows<IllegalArgumentException> { runException("#215") } // 특수문자 포함 4자리
            // 중복 테스트
            assertThrows<IllegalArgumentException> { runException("212") }  // 숫자2 중복
            assertThrows<IllegalArgumentException> { runException("551") }  // 숫자5 중복
            assertThrows<IllegalArgumentException> { runException("555") }  // 숫자5 중복
        }
    }

    override fun runMain() {
        main()
    }
}
