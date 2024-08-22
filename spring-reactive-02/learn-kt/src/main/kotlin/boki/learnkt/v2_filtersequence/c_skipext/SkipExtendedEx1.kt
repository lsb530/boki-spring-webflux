package boki.learnkt.v2_filtersequence.c_skipext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * skipLast 기본 예제
 *  - emit된 데이터 중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit된 데이터부터 건너뜀
 */
fun main() {
   Flux
       .fromIterable(SampleData.btcTopPricesPerYear)
       .skipLast(2)
       .subscribe { Logger.onNext(it.t1, it.t2) }
}