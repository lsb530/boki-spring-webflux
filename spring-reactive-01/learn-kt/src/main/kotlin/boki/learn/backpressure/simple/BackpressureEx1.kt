package boki.learn.backpressure.simple

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
fun main() {
    Flux.range(1, 5)
        .doOnNext { println("doOnNext: $it") }
        .doOnRequest { println("doOnRequest: $it") }
        .subscribe(object : BaseSubscriber<Int?>() {
            // 데이터의 요청 갯수 지정: 1
            override fun hookOnSubscribe(subscription: Subscription) {
                request(1)
            }

            // emit된 데이터를 전달받아서 처리
            override fun hookOnNext(value: Int) {
                Thread.sleep(2000L)
                println("onNext: $value\n")
                request(1)
            }
        })
}