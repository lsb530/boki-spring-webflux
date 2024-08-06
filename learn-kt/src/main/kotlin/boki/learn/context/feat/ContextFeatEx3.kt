package boki.learn.context.feat

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - 동일한 키에 대해서 write 할 경우, 해당 키에 대한 값을 덮어 쓴다.
 */
fun main() {
    val key1 = "id"

    Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        return@deferContextual Mono.just("ID: $id")
    }
        .publishOn(Schedulers.parallel())
        .contextWrite { it.put(key1, "boki") } // O <- 이 값이 출력
        .contextWrite { it.put(key1, "Sam") } // X
        .subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}