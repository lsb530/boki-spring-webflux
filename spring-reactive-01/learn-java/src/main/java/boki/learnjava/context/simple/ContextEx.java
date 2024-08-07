package boki.learnjava.context.simple;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context 개념 설명 예제 코드
 *  - contextWrite()으로 Context에 값을 쓸 수 있고, ContextView.get()을 통해서 Context에 저장된 값을 read 할 수 있다.
 *  - ContextView는 deferContextual() 또는 transformDeferredContextual()을 통해 제공된다.
 */
public class ContextEx {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> mono = Mono.deferContextual(ctx ->
                Mono.just("Hello" + " " + ctx.get(key)).doOnNext(Logger::doOnNext)
            )
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .transformDeferredContextual((mono2, ctx) -> mono2.map(data -> data + " " + ctx.get(key)))
            .contextWrite(context -> context.put(key, "Reactor"));

        // 다른 스레드에서 동작하는데도 Context를 이용해서 손쉽게 상태(데이터)를 주고받을 수 있다

        mono.subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
