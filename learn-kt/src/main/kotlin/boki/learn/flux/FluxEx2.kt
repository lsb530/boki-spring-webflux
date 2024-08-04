package boki.learn.flux

import reactor.core.publisher.Flux

fun main() {
    Flux.fromArray((2..10).toList().toTypedArray())
        .filter { num -> num > 6 }
        .map { num -> num * 2 }
        .subscribe { println("# multiply: $it") }
}