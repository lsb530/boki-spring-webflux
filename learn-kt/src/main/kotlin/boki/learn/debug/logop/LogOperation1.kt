package boki.learn.debug.logop

import boki.learn.debug.logop.FruitMap.fruits
import boki.learn.util.Logger
import reactor.core.publisher.Flux
import java.util.*

/**
 * log() operator를 사용한 예제
 */
fun main() {
    Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
        .log()
        .map { it.lowercase(Locale.getDefault()) }
        .map { it.dropLast(1) }
        .map { fruits[it] }
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )
}