package boki.learnkt.v2_filtersequence.f_next

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

/**
 * next 기본 예제
 *  - emit된 데이터중에서 첫번째 데이터만 Downstream으로 emit하고 구독을 취소
 */
fun main() {
    Flux
        .fromIterable<Tuple2<Int, Long?>>(SampleData.btcTopPricesPerYear)
        .doOnNext { Logger.doOnNext(it) }
        .filter { it.t1 == 2015 }
        .next()
        .subscribe { Logger.onNext(it.t1, it.t2) }
}