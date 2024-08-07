package boki.learnjava.stepverifier.backpressure;

import boki.learnjava.stepverifier.BackpressureExample;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

/**
 * Backpressure DROP 전략을 검증하는 예제
 * - expectError()를 사용하여 에러가 발생되었는지 검증
 * - verifyThenAssertThat()을 사용하여 검증 이후에 assertion method 를 사용하여 추가 검증을 할 수 있다.
 * - hasDiscardedElements()를 사용하여 discard된 데이터가 있는지를 검증한다. Backpressure DROP 전략은 Drop된 데이터가 discard된다.
 * - hasDiscarded()를 사용하여 discard된 데이터가 무엇인지 검증한다. Backpressure DROP 전략은 Drop된 데이터가 discard된다.
 */
class StepVerifierBackpressureTestEx3 {

    @Test
    void generateNumberTest() {
        StepVerifier
            .create(BackpressureExample.generateNumberByDropStrategy(), 1L)
            .thenConsumeWhile(num -> num >= 1)
            .expectComplete()
            .verifyThenAssertThat()
            .hasDiscardedElements()
            .hasDiscarded(2, 3, 4, 5, 6, 98, 99, 100);
            //    .hasDropped(2, 3, 4, 5, 6, 98, 99, 100); // <- Back Pressure의 drop전략하고 무관하다
    }
}
