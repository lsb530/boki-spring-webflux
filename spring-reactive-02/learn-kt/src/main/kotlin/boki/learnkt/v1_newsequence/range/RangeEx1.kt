package boki.learnkt.v1_newsequence.range

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * range() 사용 예제
 * 첫번째 파라미터(start)부터 두번째 파라미터(count)만큼 1씩 증가한 연속된 정수를 emit
 */
fun main() {
    Flux
        .range(5, 10)
        .subscribe { Logger.onNext(it) }
}