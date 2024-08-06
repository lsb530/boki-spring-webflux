package boki.learn.scheduler.other

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.newParallel()을 적용
 */
fun main() {
    val mono = Mono
        .just(1)
        .publishOn(Schedulers.newParallel("Parallel Thread", 4, true))

    mono.subscribe { data ->
        Thread.sleep(5000L)
        Logger.onNext("subscribe 1", data)
    }

    mono.subscribe { data ->
        Thread.sleep(4000L)
        Logger.onNext("subscribe 2", data)
    }

    mono.subscribe { data ->
        Thread.sleep(3000L)
        Logger.onNext("subscribe 3", data)
    }

    mono.subscribe { data ->
        Thread.sleep(2000L)
        Logger.onNext("subscribe 4", data)
    }

    Thread.sleep(6000L)
}