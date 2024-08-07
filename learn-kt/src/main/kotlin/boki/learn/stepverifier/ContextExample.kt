package boki.learn.stepverifier

import reactor.core.publisher.Mono
import reactor.util.context.ContextView
import reactor.util.function.Tuple2

class ContextExample {
    companion object {
        fun helloMessage(source: Mono<String>, key: String?): Mono<String> {
            return source
                .zipWith(Mono.deferContextual { ctx: ContextView ->
                    Mono.just(ctx.get<Any>(key!!))
                })
                .map { tuple: Tuple2<String, Any> -> tuple.t1 + ", " + tuple.t2 }
        }
    }
}