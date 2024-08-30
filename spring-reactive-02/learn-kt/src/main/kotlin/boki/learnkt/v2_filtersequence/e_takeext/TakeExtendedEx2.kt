package boki.learnkt.v2_filtersequence.e_takeext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * takeUntil 기본 예제
 *  - 파라미터로 입력되는 Predicate에 true가 될때까지 데이터를 emit
 *  - emit된 데이터에는 Predicate이 true로 match되는 데이터가 포함
 */
fun main() {
   Flux
       .fromIterable(SampleData.btcTopPricesPerYear)
       .takeUntil { it.t2 > 10_000_000 }
       .subscribe { Logger.onNext(it.t1, it.t2) }
}