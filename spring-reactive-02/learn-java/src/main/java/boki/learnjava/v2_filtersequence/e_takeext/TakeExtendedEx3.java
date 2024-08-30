package boki.learnjava.v2_filtersequence.e_takeext;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * takeUntilOther 기본 예제
 *  - 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터만
 *    Downstream으로 emit
 */
public class TakeExtendedEx3 {
    public static void main(String[] args) {
        Flux
            .interval(Duration.ofMillis(300))
            .takeUntilOther(doSomeTask())
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2000);
    }

    private static Publisher<?> doSomeTask() {
        return Mono.empty().delay(Duration.ofSeconds(1));
    }
}
