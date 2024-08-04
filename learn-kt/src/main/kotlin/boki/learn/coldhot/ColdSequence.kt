package boki.learn.coldhot

import reactor.core.publisher.Flux
import java.util.*

fun main() {
    val colorFlux = Flux.fromIterable(mutableListOf("RED", "YELLOW", "GREEN"))
        .map { obj: String -> obj.lowercase(Locale.getDefault()) }

    colorFlux.subscribe { println("# Subscriber1: $it") }
    println("-------------------------------------")
    colorFlux.subscribe { println("# Subscriber2: $it") }
}