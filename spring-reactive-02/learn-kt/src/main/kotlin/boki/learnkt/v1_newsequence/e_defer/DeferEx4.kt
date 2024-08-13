package boki.learnkt.v1_newsequence.e_defer

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono

/**
 * Defer 사용 예제
 * - 원본 데이터 소스에서 emit 되는 데이터가 없을 경우에만 Mono.defer(this::sayDefault)가 실행된다
 */
fun main() {
    Logger.info("# Start")

    val mono = Mono
        .empty<Any>()
        .switchIfEmpty(Mono.defer { sayDefault() })

    TimeUtils.sleep(3000)

    mono.subscribe { Logger.onNext(it) }
}

private fun sayDefault(): Mono<String> {
    Logger.info("# Say Hi")
    return Mono.just("Hi")
}