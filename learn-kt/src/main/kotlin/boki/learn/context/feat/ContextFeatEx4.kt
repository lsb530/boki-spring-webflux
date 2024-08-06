package boki.learn.context.feat

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 * - inner Sequence 내부에서 Context에 저장된 데이터는 inner Sequence 외부에서 읽을 수 없다.
 */
fun main() {
    val key1 = "id"

    Mono.just("boki") // name
        // 이너 시퀀스 안쪽에서 정의된 job이라는 값을 외부에서는 읽어올 수 없다
        /*.transformDeferredContextual { mono, context ->
            mono.map { context.get<String>("job") }
        }*/
        .flatMap { name ->
            Mono.deferContextual { ctx ->
                Mono.just("${ctx.get<String>(key1)}, $name")
                    .transformDeferredContextual { m, innerCtx ->
                        m.map { data -> "$data and ${innerCtx.get<String>("job")}" }
                    }
                    .contextWrite { it.put("job", "Software Engineer") }
            }
        }
        .publishOn(Schedulers.parallel())
        .contextWrite { it.put(key1, "개발꾼") }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}