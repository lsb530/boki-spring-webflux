package boki.learnkt.v2_filtersequence.e_takeext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * takeLast 기본 예제
 *  emit된 데이터중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit된 데이터만 가져옴
 */
fun main() {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .takeLast(2)
        .subscribe { Logger.onNext(it.t1, it.t2) }
}