package boki.learn.debug.logop

import boki.learn.debug.logop.FruitMap.fruits
import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import java.util.*

/**
 * log() operator와 Debug mode 를 같이 사용한 예제
 * - log()는 에러 발생 시, stacktrace와 함께 traceback도 같이 출력한다.
 */
fun main() {
    Hooks.onOperatorDebug()

    Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
        .log()
        .map { it.lowercase(Locale.getDefault()) }
        .log()
        .map { it.dropLast(1) }
        .log()
        .map { fruits[it] }
        .log()
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )
}