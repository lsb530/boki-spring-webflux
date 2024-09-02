package boki.learnkt.v3_transformsequence.c_flatmapext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples

/**
 * flatMapMany 활용 예제
 *  - Mono 에서 emit 된 데이터를 Flux 로 변환한다.
 *  - 특정 가격에 BTC를 구매했을 때 연도별 최고가일 경우 수익 금액 계산하기.
 *      수익 금액 = (현재 가격 * 투자 금액 / 구매시 가격 ) - 원금
 */
fun main() {
    val BUY_PRICE = 500
    val INVESTMENT_AMOUNT = 1000

    Mono
        .just(Tuples.of(BUY_PRICE, INVESTMENT_AMOUNT))
        .flatMapMany { calculateMaxProfitPerYear(it) }
        .subscribe { Logger.onNext(it) }
}

private fun calculateMaxProfitPerYear(buyInfo: Tuple2<Int, Int>): Flux<Long> {
    return Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .map { it.t2 * buyInfo.t2 / buyInfo.t1 - buyInfo.t2}
}