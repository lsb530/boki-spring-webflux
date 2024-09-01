package boki.learnkt.v3_transformsequence.b_flatmap

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * flatMap 기본 개념 예제
 * - 구구단 3단 출력 예제
 */
fun main() {
    Flux
        .just(3)
        .flatMap { Flux.range(1, 9).map { n -> "$it * $n = ${it * n}" } }
        .subscribe { Logger.onNext(it) }
}