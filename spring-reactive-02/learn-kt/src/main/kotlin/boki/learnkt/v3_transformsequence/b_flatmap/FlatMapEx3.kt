package boki.learnkt.v3_transformsequence.b_flatmap

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * flatMap 기본 개념 예제
 * - 비동기적으로 동작할 경우, emit되는 순서를 보장하지 않는다
 */
fun main() {
    Flux
        .range(2, 8)
        .flatMap {
            Flux
                .range(1, 9)
                .publishOn(Schedulers.parallel())
                .map { n -> "$it * $n = ${it * n}" }
        }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(200L)
}