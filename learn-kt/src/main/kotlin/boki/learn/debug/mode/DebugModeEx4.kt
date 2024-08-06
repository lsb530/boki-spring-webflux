package boki.learn.debug.mode

import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import java.util.*

/**
 * onOperatorDebug() Hook 메서드를 이용한 Debug mode 예제
 */
fun main() {
    val fruits: MutableMap<String, String> = java.util.HashMap()
    initFruitMap(fruits)

    Hooks.onOperatorDebug()

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