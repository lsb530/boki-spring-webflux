package boki.learnkt.v1_newsequence.range

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import java.text.NumberFormat
import java.util.*

/**
 * range() 사용 예제
 *  range()를 사용해서 list의 특정 index에 해당하는 데이터를 조회하는 예제
 */
fun main() {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)

    Flux
        .range(7, 5)
        .map { SampleData.btcTopPricesPerYear[it] }
        .subscribe { Logger.onNext("${it.t1}'s ${numberFormat.format(it.t2)}") }
}