package boki.learnkt.v3_transformsequence.b_flatmap

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import reactor.util.function.Tuples

/**
 * flatMap 활용 예제
 * - 특정 가격에 BTC를 구매했을 때 연도별 최고가일 경우 수익 금액 계산하기
 *      수익 금액 = (BTC 최고 가격 * 투자 금액 / 구매 당시 가격) - 원금
 */
fun main() {
    val BUY_PRICE = 500
    val INVESTMENT_AMOUNT = 1000

    Flux
        .just(Tuples.of(BUY_PRICE, INVESTMENT_AMOUNT))
        .flatMap { calculateMaxProfitPerYear(it) }
        .subscribe { Logger.onNext(it) }
}

private fun calculateMaxProfitPerYear(buyInfo: Tuple2<Int, Int>): Flux<Long> {
    return Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .map { it.t2 * buyInfo.t2 / buyInfo.t1 - buyInfo.t2 }
}