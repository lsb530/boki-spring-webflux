package boki.learnkt.v2_filtersequence.a_filter

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * Filter 기본 개념 예제
 */
fun main() {
    Flux
        .range(1, 20)
        .filter { it % 2 == 0 }
        .subscribe { Logger.onNext(it) }
}