package boki.learn.stepverifier.backpressure

import boki.learn.stepverifier.BackpressureExample
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

/**
 * Backpressure 전략에 따라 Exception이 발생하는 예제
 * - request 데이터 개수보다 많은 데이터가 emit 되어 OverFlowException이 발생
 * - OverFlowException이 발생하게 된 데이터는 discard 된다.
 * - 나머지 emit 된 데이터들은 Hooks.onNextDropped()에 의해 drop된다.
 */
class StepVerifierBackpressureTestEx1 {

    @Test
    fun generateNumberTest() {
        StepVerifier
            .create<Int>(BackpressureExample.generateNumberByErrorStrategy(), 1L)
            .thenConsumeWhile { num -> num >= 1 } // emit 된 데이터들을 소비한다.
            .verifyComplete() // overrun
    }
}