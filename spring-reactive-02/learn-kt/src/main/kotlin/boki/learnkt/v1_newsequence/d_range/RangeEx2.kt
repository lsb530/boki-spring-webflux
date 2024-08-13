package boki.learnkt.v1_newsequence.d_range

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * range() 사용 예제
 * 명령형 형식의 for 문을 대체하는 예제
 */
fun main() {
    val coinNames = SampleData.coinNames

    Flux
        .range(0, coinNames.size)
        .subscribe { Logger.onNext(coinNames[it]) }
}