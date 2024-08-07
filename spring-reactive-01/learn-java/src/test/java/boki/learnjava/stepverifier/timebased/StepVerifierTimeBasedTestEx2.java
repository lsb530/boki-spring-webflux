package boki.learnjava.stepverifier.timebased;

import boki.learnjava.stepverifier.TimeBasedExample;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * 실제 시간을 가상 시간으로 대체하는 테스트 예제
 * - thenAwait(Duration)을 통해 특정 시간만큼 가상으로 기다린다.
 * - 즉, 특정 시간을 기다린 것처럼 시간을 당긴다.
 */
class StepVerifierTimeBasedTestEx2 {

    @Test
    void getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime(() -> TimeBasedExample.getCOVID19Count(
                    Flux.interval(Duration.ofHours(12)).take(1)
                )
            )
            .expectSubscription()
            // Emit 시점의 타임라인을 현재로 끌어온다
            .thenAwait(Duration.ofHours(12))
            .expectNextCount(11)
            .expectComplete()
            .verify();
    }
}
