package boki.learn.debug.mode

import boki.learn.util.Logger
import reactor.core.publisher.Flux
import java.util.*

/**
 * Non-Debug mode
 */
fun main() {
    val fruits: MutableMap<String, String> = HashMap()
    initFruitMap(fruits)

    Flux.fromArray(arrayOf("BANANAS", "APPLES", "PEARS", "MELONS"))
        .map { it.lowercase(Locale.getDefault()) }
        .map { it.dropLast(1) }
        // .mapNotNull { fruits[it] }
        .map { fruits[it] } // NPE
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )
}

private fun initFruitMap(fruits: MutableMap<String, String>) {
    fruits["banana"] = "바나나"
    fruits["apple"] = "사과"
    fruits["pear"] = "배"
    fruits["grape"] = "포도"
}