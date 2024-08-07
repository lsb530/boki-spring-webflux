package boki.learn.scheduler.operator

import boki.learn.util.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * subscribeOn( )과 publishOn( )이 같이 있다면, publishOn( )을 만나기 전 까지의 Upstream Operator 체인은
 * subscribeOn( )에서 지정한 쓰레드에서 실행되고, publishOn( )을 만날때마다
 * publishOn( ) 아래의 Operator 체인 downstream은 publishOn( )에서 지정한 쓰레드에서 실행된다.
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .subscribeOn(Schedulers.boundedElastic())
        .filter { it > 3 }
        .doOnNext { Logger.doOnNext("filter", it) }
        .publishOn(Schedulers.parallel())
        .map { it * 10 }
        .doOnNext { Logger.doOnNext("map", it) }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(500L)
}