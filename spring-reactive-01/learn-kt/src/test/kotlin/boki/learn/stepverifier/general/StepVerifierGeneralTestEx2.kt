package boki.learn.stepverifier.general

import boki.learn.stepverifier.GeneralExample
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

/**
 * expectNext()를 사용하여 emit 된 n 개의 데이터를 검증하는 예제
 */
class StepVerifierGeneralTestEx2 {

    @Test
    fun sayHelloReactorTest() {
        StepVerifier
            .create(GeneralExample.sayHelloReactor())
            .expectSubscription()
            .expectNext("Hello")
            .expectNext("Reactor")
            .expectComplete()
            .verify()
    }
}