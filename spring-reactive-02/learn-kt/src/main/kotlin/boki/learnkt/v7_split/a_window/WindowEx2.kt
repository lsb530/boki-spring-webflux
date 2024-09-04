package boki.learnkt.v7_split.a_window

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux
import reactor.math.MathFlux

/**
 * window 활용 예제
 * - window를 이용해 2021년도 월별 도서 판매량에서 분기별 판매량을 계산한다.
 * - 즉, 월별 데이터를 3개월씩 window에 포함 한 후, 분기별 판매량을 계산한다.
 */
fun main() {
    Flux
        .fromIterable(SampleData.monthlyBookSales2021)
        .window(3)
        .flatMap { MathFlux.sumInt(it) }
        .subscribe(
            object : BaseSubscriber<Any>() {
                override fun hookOnSubscribe(subscription: Subscription) {
                    subscription.request(2)
                }

                override fun hookOnNext(value: Any) {
                    Logger.onNext(value)
                    request(2)
                }
            }
        )
}