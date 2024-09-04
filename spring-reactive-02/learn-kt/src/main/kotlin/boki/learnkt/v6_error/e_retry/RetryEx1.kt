package boki.learnkt.v6_error.e_retry

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import java.time.Duration

/**
 * retry 기본 개념 예제
 * - 에러가 발생했을 때, 지정된 횟수만큼 원본 Publisher의 Sequence를 다시 구독한다.
 */
fun main() {
    Hooks.onNextDropped { Logger.onNextDropped(it) }

    val count: Array<Int> = arrayOf(1)
    Flux
        .range(1, 3)
        .delayElements(Duration.ofSeconds(1))
        .map { num ->
            if (num == 3 && count[0] == 1) {
                // if (num == 3) {
                count[0]++
                TimeUtils.sleep(1000L)
            }
            return@map num
        }
        .timeout(Duration.ofMillis(1500L))
        .onBackpressureDrop()
        .retry(1)
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
            { Logger.onComplete() },
        )

    TimeUtils.sleep(8000L)
}