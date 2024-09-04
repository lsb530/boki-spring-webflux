package boki.learnkt.v5_delayandtimeout

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * delayElements 기본 개념 예제
 *  - upstream에서의 데이터 emit을 파라미터로 입력한 시간만큼 지연 시킨다.
 *  - delayElements를 거친 데이터는 별도의 쓰레드(parallel)에서 실행된다.
 */
fun main() {
    Flux
        .range(1, 10)
        .delayElements(Duration.ofMillis(500))
        .doOnNext { Logger.doOnNext(it) }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(6000)
}