package boki.learnkt.v2_filtersequence.e_takeext

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * takeUntilOther 기본 예제
 *  - 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터만
 *    Downstream으로 emit
 */
fun main() {
    Flux
        .interval(Duration.ofMillis(300))
        .takeUntilOther(doSomeTask())
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2000)
}

private fun doSomeTask(): Publisher<*> {
    return Mono.delay(Duration.ofSeconds(1))
}