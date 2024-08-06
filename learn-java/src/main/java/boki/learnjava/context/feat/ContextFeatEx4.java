package boki.learnjava.context.feat;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Context의 특징
 * - inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 * - inner Sequence 내부에서 Context에 저장된 데이터는 inner Sequence 외부에서 읽을 수 없다.
 */
public class ContextFeatEx4 {
    public static void main(String[] args) {
        String key1 = "id";

        Mono.just("boki") // name
            // 이너 시퀀스 안쪽에서 정의된 job이라는 값을 외부에서는 읽어올 수 없다
            // .transformDeferredContextual((stringMono, contextView) -> contextView.get("job"))
            .flatMap(name -> Mono.deferContextual(ctx -> // Inner Sequence
                    Mono.just(ctx.get(key1) + ", " + name)
                        .transformDeferredContextual((mono, innerCtx) ->
                            mono.map(data -> data + " and " + innerCtx.get("job"))
                        )
                        .contextWrite(context -> context.put("job", "Software Engineer"))
                )
            )
            .publishOn(Schedulers.parallel())
            .contextWrite(context -> context.put(key1, "개발꾼"))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100L);
    }
}