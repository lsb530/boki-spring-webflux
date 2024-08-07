package boki.learnjava.stepverifier.timebased;

import boki.learnjava.stepverifier.TimeBasedExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

import java.time.Duration;

/**
 * expectNoEvent(Duration)으로 지정된 대기 시간동안 이벤트가 없을을 확인하는 예제
 */
class StepVerifierTimeBasedTestEx4 {

    @Test
    void getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime(() -> TimeBasedExample.getVoteCount(
                    Flux.interval(Duration.ofMinutes(1))
                )
            )
            .expectSubscription()
            .expectNoEvent(Duration.ofMinutes(1)) // 데이터가 Emit 되기까지의 1분동안은 아무런 이벤트가 발생하지 않음을 테스트
            .expectNext(Tuples.of("중구", 15400))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNoEvent(Duration.ofMinutes(1))
            .expectNextCount(4)
            .expectComplete()
            .verify();
    }
}
