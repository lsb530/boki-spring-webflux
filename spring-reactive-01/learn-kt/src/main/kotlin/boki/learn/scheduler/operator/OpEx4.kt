package boki.learn.scheduler.operator

import boki.learn.util.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * subscribeOn()은 구독 직후에 실행 될 쓰레드를 지정한다.
 * 즉, 원본 Publisher의 실행 쓰레드를 subscribeOn()에서 지정한 쓰레드로 바꾼다.
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext { Logger.doOnNext("fromArray", it) }
        .filter { it > 3 }
        .doOnNext { Logger.doOnNext("filter", it) }
        .map { it * 10 }
        .doOnNext { Logger.doOnNext("map", it) }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(500L)
}