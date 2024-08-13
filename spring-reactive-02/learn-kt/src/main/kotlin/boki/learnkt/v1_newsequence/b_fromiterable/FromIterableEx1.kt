package boki.learnkt.v1_newsequence.b_fromiterable

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * fromIterable() 사용 예제
 * Iterable 구현 클래스를 파라미터로 입력받아 차례대로 emit
 */
fun main() {
    Flux
        .fromIterable(SampleData.coinNames)
        .subscribe { Logger.onNext(it) }
}