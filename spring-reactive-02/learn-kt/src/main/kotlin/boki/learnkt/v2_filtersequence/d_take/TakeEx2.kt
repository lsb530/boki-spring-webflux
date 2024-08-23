package boki.learnkt.v2_filtersequence.d_take

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * take 기본 개념 예제
 *  - 파라미터로 입력한 시간 내에 Upstream에서 emit된 데이터만 Downstream으로 emit
 */
fun main() {
    Flux
        .interval(Duration.ofSeconds(1))
        .doOnNext { Logger.doOnNext(it) }
        .take(Duration.ofSeconds(2))
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(4000L)
}