package boki.learn.stepverifier.general

import boki.learn.stepverifier.GeneralExample
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions

/**
 * onNext signal 을 통해 emit 된 데이터의 개수를 검증하는 예제
 * - 검증에 실패할 경우에는 StepVerifierOptions 에서 지정한 Scenario Name이 표시된다.
 */
class StepVerifierGeneralTestEx6 {

    @Test
    fun rangeNumberFailTest() {
        val source = Flux.range(0, 1000)
        StepVerifier
            .create<Int>(
                GeneralExample.takeNumber(source, 500),
                StepVerifierOptions.create().scenarioName("Verify from 0 to 499")
            )
            .expectSubscription()
            .expectNext(0)
            .expectNextCount(498)
            .expectNext(499)
            .expectComplete()
            .verify()
    }

    @Test
    fun rangeNumberSuccessTest() {
        val source = Flux.range(0, 1000)
        StepVerifier
            .create<Int>(
                GeneralExample.takeNumber(source, 500),
                StepVerifierOptions.create().scenarioName("Verify from 0 to 499")
            )
            .expectSubscription()
            .expectNext(0)
            .expectNextCount(498)
            .expectNext(500)
            .expectError()
    }
}