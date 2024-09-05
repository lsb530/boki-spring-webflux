package boki.learnkt.v8_multicast.a_publish

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * publish() 개념 예제
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - connect()가 호출 되기 전 까지는 데이터를 emit하지 않는다.
 */
fun main() {
    val flux = Flux
        .range(1, 5)
        .delayElements(Duration.ofMillis(300L))
        .publish() // wait for connect()

    TimeUtils.sleep(500L)
    flux.subscribe { Logger.onNext("subscriber1: $it") }

    TimeUtils.sleep(200L)
    flux.subscribe { Logger.onNext("subscriber2: $it") }

    flux.connect()

    TimeUtils.sleep(1000L)
    flux.subscribe { Logger.onNext("subscriber3: $it") }

    TimeUtils.sleep(2000L)
}