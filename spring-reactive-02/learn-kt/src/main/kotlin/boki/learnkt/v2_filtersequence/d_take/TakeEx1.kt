package boki.learnkt.v2_filtersequence.d_take

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * take 기본 개념 예제
 *  - 파라미터로 입력한 숫자만큼만 Downstream으로 emit
 */
fun main() {
    Flux
        .interval(Duration.ofSeconds(1))
        .doOnNext { Logger.doOnNext(it) }
        .take(3)
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(5000L)
}