package boki.learnkt.v2_filtersequence.a_filter

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * 비트코인의 연간 최고가격이 1000만원을 넘는 연도와 가격을 필터링하는 예제
 */
fun main() {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .filter { it.t2 > 10_000_000 }
        .subscribe { Logger.onNext("${it.t1}:${it.t2}") }
}