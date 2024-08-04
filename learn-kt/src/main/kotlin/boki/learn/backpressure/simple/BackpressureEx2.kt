package boki.learn.backpressure.simple

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
fun main() {
    var count = 0
    Flux.range(1, 5)
        .doOnNext { println("doOnNext: $it") }
        .doOnRequest { println("\ndoOnRequest: $it") }
        .subscribe(object : BaseSubscriber<Int?>() {
            // 데이터의 요청 갯수 지정: 2
            override fun hookOnSubscribe(subscription: Subscription) {
                request(2)
            }

            // emit된 데이터를 전달받아서 처리
            override fun hookOnNext(value: Int) {
                count++
                println("onNext: $value")
                if (count == 2) {
                    println("count $count")
                    Thread.sleep(2000L)
                    request(2)
                    count = 0
                }
            }
        })
}