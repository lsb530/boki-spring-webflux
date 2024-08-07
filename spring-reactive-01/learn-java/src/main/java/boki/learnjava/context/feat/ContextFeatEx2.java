package boki.learnjava.context.feat;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context의 특징
 * - Context는 체인의 맨 아래에서부터 위로 전파된다.
 * - 따라서 Operator 체인에서 Context read 읽는 동작이 Context write 동작 밑에 있을 경우에는 write된 값을 read할 수 없다.
 */
public class ContextFeatEx2 {
    public static void main(String[] args) {
        final String key1 = "id";
        final String key2 = "name";

        Mono
            .deferContextual(ctx ->
                Mono.just(ctx.get(key1))
            )
            .publishOn(Schedulers.parallel())
            // 컨텍스트는 아래에서부터 올라오기 때문에 의미없는 코드
            .contextWrite(context -> context.put(key2, "boki"))
            .transformDeferredContextual((mono, ctx) ->
                mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Sam")) // 이 시점의 key2는 값이 없음
            )
            .contextWrite(context -> context.put(key1, "개발꾼"))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
