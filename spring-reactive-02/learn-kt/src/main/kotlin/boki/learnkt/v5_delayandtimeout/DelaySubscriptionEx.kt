package boki.learnkt.v5_delayandtimeout

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * delaySubscription 기본 개념 예제
 *  - 파라미터로 입력한 시간만큼 구독을 지연 시킨다.
 */
fun main() {
    Flux
        .range(1, 10)
        .doOnSubscribe { Logger.info("# doOnSubscribe > upstream") }
        .delaySubscription(Duration.ofSeconds(2))
        .doOnSubscribe { Logger.info("# doOnSubscribe > downstream") }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2500)
}