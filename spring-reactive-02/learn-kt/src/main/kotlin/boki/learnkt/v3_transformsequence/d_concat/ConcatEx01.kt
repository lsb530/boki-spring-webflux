package boki.learnkt.v3_transformsequence.d_concat

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * concat 기본 개념 예제
 *  - 파라미터로 입력된 Publisher Sequence 들을 연결해서 차례대로 emit한다.
 */
fun main() {
    Flux
        .concat(Flux.just(1, 2, 3), Flux.just(4, 5))
        .subscribe { Logger.onNext(it) }
}