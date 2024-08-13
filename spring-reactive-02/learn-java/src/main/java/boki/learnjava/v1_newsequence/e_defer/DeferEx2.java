package boki.learnjava.v1_newsequence.e_defer;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력되는 Sequence는 Upstream에서 emit되는 데이터가 없을경우 Downstream에 emit한다
 * - 하지만 파라미터로 입력된 sayDefault()는 switchIfEmpty()가 선언된 시점에 이미 호출이 되기때문에
 * - downstream에 데이터를 emit 하지는 않지만 불필요한 메서드 호출이 발생한다
 */
public class DeferEx2 {
    public static void main(String[] args) {
        Logger.info("# Start");

        Mono
            .just("Hello")
            .delayElement(Duration.ofSeconds(2))
            .switchIfEmpty(sayDefault()) // Upstream에서 emit 받아오는 데이터가 없을 경우 실행하는 코드
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);
    }

    private static Mono<String> sayDefault() {
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }
}
