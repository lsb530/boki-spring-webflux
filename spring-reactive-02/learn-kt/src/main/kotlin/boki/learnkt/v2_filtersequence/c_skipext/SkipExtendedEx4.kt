package boki.learnkt.v2_filtersequence.c_skipext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * skipWhile 기본 예제
 *  - 파라미터로 입력되는 Predicate가 true가 될때까지 건너뜀
 */
fun main() {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .skipWhile { it.t2 < 10_000_000 }
        .subscribe { Logger.onNext(it.t1, it.t2) }
}