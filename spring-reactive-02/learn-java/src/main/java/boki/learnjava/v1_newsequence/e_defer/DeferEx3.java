package boki.learnjava.v1_newsequence.e_defer;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력되는 Sequence는 Upstream에서 emit되는 데이터가 없을경우 Downstream에 emit한다
 * - 불필요한 호출을 방지하기 위해 실제 필요한 시점에 데이터를 emit하도록 defer()를 사용한다
 */
public class DeferEx3 {
    public static void main(String[] args) {
        Logger.info("# Start");

        Mono
            .just("Hello")
            .delayElement(Duration.ofSeconds(3))
            .switchIfEmpty(Mono.defer(() -> sayDefault()))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(3500);
    }

    private static Mono<String> sayDefault() {
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }
}
