package boki.learn.context.simple

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context
import reactor.util.context.ContextView

/**
 * Context 개념 설명 예제 코드
 *  - contextWrite()으로 Context에 값을 쓸 수 있고, ContextView.get()을 통해서 Context에 저장된 값을 read 할 수 있다.
 *  - ContextView는 deferContextual() 또는 transformDeferredContextual()을 통해 제공된다.
 */
fun main() {
    val key = "message"
    val mono = Mono.deferContextual { ctx ->
        Mono.just<String>("Hello ${ctx.get<String>(key)}").doOnNext { Logger.doOnNext(it) }
    }
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel())
        .transformDeferredContextual { m: Mono<String>, c: ContextView -> m.map { "$it ${c.get<String>(key)}" } }
        .contextWrite { ctx: Context -> ctx.put(key, "Reactor") }

    // 다른 스레드에서 동작하는데도 Context를 이용해서 손쉽게 상태(데이터)를 주고받을 수 있다
    mono.subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}