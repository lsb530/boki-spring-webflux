package boki.learnjava.stepverifier.testpublisher;

import boki.learnjava.stepverifier.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher 를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - next() 사용
 */
class TestPublisherTestEx1 {

    @Test
    void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
            .create(GeneralExample.divideByTwo(source.flux()))
            .expectSubscription()
            .then(() -> source.next(2, 4, 6, 8, 10))
            .expectNext(1, 2, 3, 4, 5)
            .expectComplete()
            .verify();
    }
}