package boki.learnjava.stepverifier.timebased;

import boki.learnjava.stepverifier.TimeBasedExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;

/**
 * 실제 시간을 가상 시간으로 대체하는 테스트 예제
 * - 특정 시간만큼 시간을 앞당긴다.
 */
class StepVerifierTimeBasedTestEx1 {

    @Test
    void getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                    Flux.interval(Duration.ofHours(12)).take(1)
                )
            )
            .expectSubscription()
            // 현재에서 Emit 시점의 타임라인으로 이동한다
            .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(12)))
            .expectNextCount(11)
            .expectComplete()
            .verify();
    }
}
