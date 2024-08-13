package boki.learnkt.v1_newsequence.g_generate

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * generate 개념 이해 예제
 * - 2016년도 이후의 해당 연도(2017 - 2021년)의 BTC 최고가 금액을 출력
 */
fun main() {
    val map = SampleData.getBtcTopPricesPerYearMap()

    Flux
        .generate(
            { 2017 },
            { state, sink ->
                if (state > 2021) {
                    sink.complete()
                }
                else {
                    sink.next(map[state])
                }
                return@generate state + 1
            }
        )
        .subscribe { Logger.onNext(it) }
}