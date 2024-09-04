package boki.learnjava.v6_error.e_retry;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.time.Duration;

/**
 * retry 기본 개념 예제
 * - 에러가 발생했을 때, 지정된 횟수만큼 원본 Publisher의 Sequence를 다시 구독한다.
 */
public class RetryEx1 {
    public static void main(String[] args) throws InterruptedException {
        Hooks.onNextDropped(Logger::onNextDropped);

        final int[] count = {1};
        Flux
            .range(1, 3)
            .delayElements(Duration.ofSeconds(1))
            .map(num -> {
                if (num == 3 && count[0] == 1) {
                // if (num == 3) {
                    count[0]++;
                    TimeUtils.sleep(1000);
                }

                return num;
            })
            .timeout(Duration.ofMillis(1500))
            .onBackpressureDrop()
            .retry(1)
            .subscribe(
                Logger::onNext,
                Logger::onError,
                Logger::onComplete
            );

        TimeUtils.sleep(8000);
    }
}
