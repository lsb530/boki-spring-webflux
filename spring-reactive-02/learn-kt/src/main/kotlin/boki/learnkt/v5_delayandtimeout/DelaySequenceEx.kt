package boki.learnkt.v5_delayandtimeout

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * delaySequence 기본 개념 예제
 *  - 구독은 즉시 이루어지지만 최초의 데이터 emit은 파라미터로 입력한 시간만큼 지연 시킨다.
 */
fun main() {
    Flux
        .range(1, 10)
        .doOnSubscribe { Logger.doOnSubscribe() }
        .delaySequence(Duration.ofSeconds(2))
        .doOnSubscribe { Logger.doOnSubscribe() }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2500)
}