package boki.learnjava.context.feat;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context의 특징
 * - 동일한 키에 대해서 write 할 경우, 해당 키에 대한 값을 덮어 쓴다.
 */
public class ContextFeatEx3 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.deferContextual(ctx ->
                Mono.just("ID: " + ctx.get(key1))
            )
            .publishOn(Schedulers.parallel())
            .contextWrite(context -> context.put(key1, "Boki")) // 이 값이 출력
            .contextWrite(context -> context.put(key1, "Sam")) // 아래에서 위로
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}
