package boki.learn.scheduler.operator

import boki.learn.util.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Operator 체인에서 publishOn( )이 호출되면 publishOn( ) 호출 이후의 Operator 체인은
 * 다음 publisherOn( )을 만나기전까지 publishOn( )에서 지정한 Thread에서 실행이 된다.
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .doOnNext { Logger.doOnNext("fromArray", it) }
        .publishOn(Schedulers.parallel())
        .filter { it > 3 }
        .doOnNext { Logger.doOnNext("filter", it) }
        .map { it * 10 }
        .doOnNext { Logger.doOnNext("map", it) }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(500L)
}