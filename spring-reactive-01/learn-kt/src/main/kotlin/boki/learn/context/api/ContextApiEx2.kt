package boki.learn.context.api

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * Context API 예제 코드
 * - pullAll(ContextView) API 사용
 */
fun main() {
    val key1 = "id"
    val key2 = "name"
    val key3 = "country"

    Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        val name: String = ctx[key2]
        val country: String = ctx[key3]

        Mono.just("ID: $id, Name: $name, Country: $country")
    }
        .publishOn(Schedulers.parallel())
        .contextWrite { it.putAll(Context.of(mapOf(key2 to "boki", key3 to "Korea")).readOnly()) }
        .contextWrite { it.put(key1, "개발꾼") }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}