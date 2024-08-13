package boki.learnkt.v1_newsequence.fromstream

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * fromStream() 사용 예제
 * Stream을 파라미터로 입력받아 Stream에 포함된 데이터를 차례대로 emit
 */
fun main() {
    Flux
        .fromStream { SampleData.coinNames.stream() }
        .subscribe { Logger.onNext(it) }
}