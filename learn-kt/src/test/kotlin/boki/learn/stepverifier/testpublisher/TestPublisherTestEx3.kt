package boki.learn.stepverifier.testpublisher

import org.junit.jupiter.api.Test
import reactor.test.StepVerifier
import reactor.test.publisher.TestPublisher

/**
 * TestPublisher 를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - emit() 사용
 */
class TestPublisherTestEx3 {

    @Test
    fun divideByTwoTest() {
        val source = TestPublisher.create<Int>()

        StepVerifier
            .create(source.flux().log())
            .expectSubscription()
            .then { source.emit(1, 2, 3) }
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectComplete()
            .verify()
    }
}