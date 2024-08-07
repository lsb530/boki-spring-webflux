package boki.learn.debug.logop

import boki.learn.debug.logop.FruitMap.fruits
import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.*

/**
 * log() operator Custom Category 를 사용하는 예제
 */
fun main() {
    Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
        .subscribeOn(Schedulers.boundedElastic())
        .log("Fruit.Source")
        .publishOn(Schedulers.parallel())
        .map { it.lowercase(Locale.getDefault()) }
        .log("Fruit.LowerCase")
        .map { it.dropLast(1) }
        .log("Fruit.DropLast")
        .map { fruits[it] }
        .log("Fruit.Name")
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )

    Thread.sleep(100L)
}