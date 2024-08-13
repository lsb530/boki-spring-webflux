package boki.learnkt.v1_newsequence.a_justorempty

import boki.learnkt.util.Logger
import reactor.core.publisher.Mono

/**
 * just()에 null 값을 입력하면 NullPointerException이 발생
 */
fun main() {
    // Mono.just<Any?>(null) // 코틀린에서는 null처리를 해주지 않으면 실행되지 않음
    Mono.just<Any?>(Unit) // 코틀린에서는 null처리를 해주지 않으면 실행되지 않음
        .log()
        .subscribe { Logger.onNext(it) }
}