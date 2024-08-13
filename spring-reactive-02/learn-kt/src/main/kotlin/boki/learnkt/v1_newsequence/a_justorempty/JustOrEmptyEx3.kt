package boki.learnkt.v1_newsequence.a_justorempty

import boki.learnkt.util.Logger
import reactor.core.publisher.Mono
import java.util.*

/**
 * justOrEmpty() 사용 예
 * 인자로 Optional.isPresent()가 true가 아니라면, onNext emit 없이 onComplete만 emit
 */
fun main() {
    Mono.justOrEmpty(Optional.ofNullable(null))
        .log()
        .subscribe { Logger.onNext(it) }
}