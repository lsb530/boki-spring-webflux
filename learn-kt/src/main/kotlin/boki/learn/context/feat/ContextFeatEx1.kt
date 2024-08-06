package boki.learn.context.feat

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - Context는 각각의 구독을 통해 Reactor Sequence에 연결 되며 체인의 각 연산자가 연결된 Context에 접근할 수 있어야 한다.
 * - Context는 구독이 발생할 때마다 하나씩 연결된다
 */
fun main() {
    val key1 = "id"

    val mono = Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        Mono.just("ID: $id")
    }
        .publishOn(Schedulers.parallel())

    mono.contextWrite { it.put(key1, "boki") }
        .subscribe { Logger.onNext("subscriber 1", it) }

    mono.contextWrite { it.put(key1, "개발꾼") }
        .subscribe { Logger.onNext("subscriber 2", it) }

    Thread.sleep(100L)
}