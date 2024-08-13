package boki.learnjava.v1_newsequence.e_defer;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Mono;

/**
 * Defer 사용 예제
 * - 원본 데이터 소스에서 emit 되는 데이터가 없을 경우에만 Mono.defer(this::sayDefault)가 실행된다
 */
public class DeferEx4 {
    public static void main(String[] args) {
        Logger.info("# Start");

        Mono<Object> mono = Mono
            .empty()
            .switchIfEmpty(Mono.defer(DeferEx4::sayDefault));

        TimeUtils.sleep(3000);
        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> sayDefault() {
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }
}
