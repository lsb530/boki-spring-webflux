package boki.learn.flux

import reactor.core.publisher.Mono

fun main() {
    // Mono + Mono = Flux
    val flux = Mono.justOrEmpty<Any>(null)
        // 람다로 처리하면 값이 출력되지 않는다(적절한 인자 필요)
        // .concatWith { Mono.justOrEmpty<Any>("Jobs") }
        .concatWith(Mono.justOrEmpty<Any>("Jobs"))

    flux.subscribe { println("# result: $it") }
}