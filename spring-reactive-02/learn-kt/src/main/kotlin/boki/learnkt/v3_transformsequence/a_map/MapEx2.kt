package boki.learnkt.v3_transformsequence.a_map

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * map 활용 예제
 *  - 2021년도의 비트코인 최고가 시점의 수익률을 계산하는 예제
 */
fun main() {
    val buyPrice = 40_000_000.0
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .filter { it.t1 == 2021 }
        .doOnNext { Logger.doOnNext(it) }
        .map { calculateProfitRate(buyPrice, it.t2) }
        .subscribe { Logger.onNext("$it%") }
}

private fun calculateProfitRate(buyPrice: Double, topPrice: Long): Double {
    return (topPrice - buyPrice) / buyPrice * 100
}