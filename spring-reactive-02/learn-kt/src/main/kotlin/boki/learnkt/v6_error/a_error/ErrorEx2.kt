package boki.learnkt.v6_error.a_error

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.zip.DataFormatException

/**
 * error Operator 활용 예제 코드
 * flatMap처럼 Inner Sequence가 존재하는 경우, 조건에 따라 onError Signal을 전송할 수 있다.
 */
fun main() {
    Flux
        .just('a', 'b', 'c', '3', 'd')
        .flatMap { convert(it) }
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) }
        )
}

private fun convert(ch: Char): Mono<String> {
    if (!Character.isAlphabetic(ch.code)) {
        return Mono.error(DataFormatException("Not Alphabetic"))
    }
    return Mono.just("Converted to " + ch.uppercaseChar())
}