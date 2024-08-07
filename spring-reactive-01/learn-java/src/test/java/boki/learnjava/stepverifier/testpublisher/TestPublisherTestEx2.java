package boki.learnjava.stepverifier.testpublisher;

import boki.learnjava.stepverifier.GeneralExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher 를 사용해서 서비스 로직의 메서드에 대한 Unit Test 를 실시하는 예제
 * - 정상 동작하는 TestPublisher
 * - next() 사용
 * - 에러 발생 여부 검증
 */
class TestPublisherTestEx2 {

    @Test
    void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
            .create(GeneralExample.occurError(source.flux()))
            .expectSubscription()
            .then(() -> source.next(2, 4, 6, 8, 10))
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectError()
            .verify();
    }

    @Test
    void divideByTwoTest2() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier
            .create(GeneralExample.occurError(source.flux()))
            .expectSubscription()
            .then(() -> {
                source.next(2, 4, 6, 8);
                source.error(new ArithmeticException());
            })
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectError()
            .verify();
    }
}