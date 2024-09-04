package boki.learnkt.v6_error.a_error

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.zip.DataFormatException

/**
 * error Operator 활용 예제 코드
 * - Sequence 외부에서 체크 예외 발생 시, Mono 또는 Flux로 래핑해서 onError Signal을 전송할 수 있다.
 */
fun main() {
    Flux
        .just('a', 'b', 'c', '3', 'd')
        .flatMap { letter: Char ->
            try {
                return@flatMap convert(letter)
            } catch (e: DataFormatException) {
                return@flatMap Mono.error<String>(e)
            }
        }
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) }
        )
}

@Throws(DataFormatException::class)
private fun convert(ch: Char): Mono<String> {
    // error signal이 아니라 Java의 체크 예외를 throw하는 클래스를 이용하는 경우.
    CharacterValidator.isAlphabeticCharacter(ch)
    return Mono.just("Converted to " + ch.uppercaseChar())
}