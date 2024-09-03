package boki.learnkt.v4_peeksequence

import boki.learnkt.util.Logger
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * DoOnXXXX() Operator의 기본 개념 예제
 */
fun main() {
    Flux
        .range(1, 5)
        .doFinally { Logger.info("doFinally() > range") } // 6
        .doOnNext { data -> Logger.doOnNext("range", data) } // 4
        .doOnRequest { Logger.info("doOnRequest > range: {}", 1) } // 3
        .doOnSubscribe { Logger.info("doOnSubscribe() > range") } // 2
        .doFirst { Logger.info("doFirst() > range") } // 1
        .doOnComplete { Logger.info("doOnComplete() > range") } // 5

        .filter { num: Int -> num % 2 == 1 }
        .doOnNext { data -> Logger.doOnNext("filter", data) } // 4
        .doOnRequest { n -> Logger.info("doOnRequest > filter: $n") } // 3
        .doOnSubscribe { Logger.info("doOnSubscribe() > filter") } // 2
        .doFinally { Logger.info("doFinally() > filter") } // 6
        .doOnComplete { Logger.info("doOnComplete() > filter") } // 5
        .doFirst { Logger.info("doFirst() > filter") } // 1

        .subscribe(object : BaseSubscriber<Int>() {
            override fun hookOnSubscribe(subscription: Subscription) {
                request(1)
            }

            override fun hookOnNext(value: Int) {
                Logger.info("# hookOnNext: $value")
                request(1)
            }
        })
}