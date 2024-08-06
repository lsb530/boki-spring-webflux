package boki.learn.context.api

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.context.Context

/**
 * ContextView API 예제 코드
 */
fun main() {
    val key1 = "id"
    val key2 = "name"

    Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        val name: String = ctx[key2]
        val job: String? = ctx.getOrDefault("job", "Software Engineer")

        Mono.just("ID: $id, Name: $name, Job: $job")
    }
        .publishOn(Schedulers.parallel())
        .contextWrite(Context.of(key1, "개발꾼", key2, "boki"))
        .subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}