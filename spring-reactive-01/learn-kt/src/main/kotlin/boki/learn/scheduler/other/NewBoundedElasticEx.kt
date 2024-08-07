package boki.learn.scheduler.other

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.newBoundedElastic()을 적용
 */
fun main() {
    val scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread")

    val mono = Mono.just(1).subscribeOn(scheduler)

    Logger.info("# Start")

    mono.subscribe { data ->
        Logger.onNext("subscribe 1 doing", data)
        Thread.sleep(3000L)
        Logger.onNext("subscribe 1 done", data)
    }

    mono.subscribe { data ->
        Logger.onNext("subscribe 2 doing", data)
        Thread.sleep(3000L)
        Logger.onNext("subscribe 2 done", data)
    }

    mono.subscribe { data -> Logger.onNext("subscribe 3 doing", data) }
    mono.subscribe { data -> Logger.onNext("subscribe 4 doing", data) }
    mono.subscribe { data -> Logger.onNext("subscribe 5 doing", data) }
    mono.subscribe { data -> Logger.onNext("subscribe 6 doing", data) }
}