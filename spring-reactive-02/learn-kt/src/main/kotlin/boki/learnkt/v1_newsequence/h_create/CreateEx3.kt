package boki.learnkt.v1_newsequence.h_create

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

/**
 * create 개념 이해 예제
 *  - Subscriber가 request 할 경우에 next signal 이벤트를 발생하는 예제
 *  - Backpressure 전략을 적용하는 예제
 */
var start = 1
var end = 4

fun main() {
    Flux.create({ emitter: FluxSink<Int> ->
        emitter.onRequest { n ->
            Logger.info("# requested: $n")
            TimeUtils.sleep(500L)
            for (i in start..end) {
                emitter.next(i)
            }
            start += 4
            end += 4
        }

        emitter.onDispose {
            Logger.info("# clean up")
        }
    }, FluxSink.OverflowStrategy.DROP)
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.parallel(), 2)
        .subscribe { data ->
            Logger.onNext(data)
        }

    TimeUtils.sleep(3000L)
}