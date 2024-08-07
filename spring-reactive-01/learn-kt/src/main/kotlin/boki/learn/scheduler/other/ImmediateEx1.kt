package boki.learn.scheduler.other

import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.immediate()을 적용하기 전.
 * 2개의 parallel 쓰레드가 할당된다.
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .publishOn(Schedulers.parallel())
        .filter { it > 3 }
        .doOnNext { Logger.doOnNext("filter", it) }
        .publishOn(Schedulers.parallel())
        .map { it * 10 }
        .doOnNext { Logger.doOnNext("map", it) }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(200L)
}