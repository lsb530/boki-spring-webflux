package boki.learnjava.v1_newsequence.e_defer;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Defer 사용 예제
 * 실제로 구독이 발생하는 시점에 데이터를 emit
 * 실행해보면 just와 defer의 emit 시간이 다르다(just는 main 메서드의 시작(24초)과 동시에 onNext, defer는 26, 28초에 onNext)
 * <pre>
 *  00:54:24.465 [main] INFO boki.learnjava.utils.Logger -- # Starting
 *  00:54:26.499 [main] INFO boki.learnjava.utils.Logger -- # just1 onNext(): 2024-08-14T00:54:24.467148
 *  00:54:26.500 [main] INFO boki.learnjava.utils.Logger -- # defer1 onNext(): 2024-08-14T00:54:26.500110
 *  00:54:28.502 [main] INFO boki.learnjava.utils.Logger -- # just2 onNext(): 2024-08-14T00:54:24.467148
 *  00:54:28.503 [main] INFO boki.learnjava.utils.Logger -- # defer2 onNext(): 2024-08-14T00:54:28.503800
 * </pre>
 */
public class DeferEx1 {
    public static void main(String[] args) {
        Logger.info("# Starting");

        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        TimeUtils.sleep(2000);

        justMono.subscribe(data -> Logger.onNext("just1", data));
        deferMono.subscribe(data -> Logger.onNext("defer1", data));

        TimeUtils.sleep(2000);

        justMono.subscribe(data -> Logger.onNext("just2", data));
        deferMono.subscribe(data -> Logger.onNext("defer2", data));
    }
}
