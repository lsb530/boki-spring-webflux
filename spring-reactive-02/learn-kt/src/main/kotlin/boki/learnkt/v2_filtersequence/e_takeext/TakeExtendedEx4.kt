package boki.learnkt.v2_filtersequence.e_takeext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * takeWhile 기본 예제
 *  - 파라미터로 입력된 Predicate이 true인 동안 emit된 데이터만 Downstream에 emit
 */
fun main() {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .takeWhile { it.t2 < 10_000_000 }
        .subscribe { Logger.onNext(it.t1, it. t2) }
}