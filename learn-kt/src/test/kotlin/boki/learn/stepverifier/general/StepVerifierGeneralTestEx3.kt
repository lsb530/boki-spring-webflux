package boki.learn.stepverifier.general

import boki.learn.stepverifier.GeneralExample
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

/**
 * - verifyComplete()을 사용하여 검증 실행 및 기대값으로 onComplete signal 이 맞는지 검증하는 예제
 *  - as(description)를 사용해서 실패한 expectXXXX()에게 description 을 지정할 수 있다.
 */
class StepVerifierGeneralTestEx3 {

    @Test
    fun sayHelloReactorTest() {
        StepVerifier
            .create(GeneralExample.sayHelloReactor())
            .expectSubscription()
            .`as`("# expect subscription")
            .expectNext("Hi")
            .`as`("# expect Hi")
            .expectNext("Reactor")
            .`as`("# expect Reactor")
            .verifyComplete()
    }
}