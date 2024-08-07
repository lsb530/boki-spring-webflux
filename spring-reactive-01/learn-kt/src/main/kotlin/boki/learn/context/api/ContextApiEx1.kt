package boki.learn.context.api

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * Context API 중에서 write API 예제 코드
 * - Context.of(...) 사용
 */
fun main() {
    val key1 = "id"
    val key2 = "name"

    val mono = Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        val name: String = ctx[key2]

        Mono.just("ID: $id, Name: $name")
    }
        .publishOn(Schedulers.parallel())
        .contextWrite { Context.of(mapOf(key1 to "개발꾼", key2 to "boki")) }

    mono.subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}