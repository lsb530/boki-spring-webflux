package boki.learn.flux

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    Flux.concat<Any>(
        Flux.just("Venus"),
        Mono.just("Earth"),
        Flux.just("Mars")
    )
        .collectList() // 주석하고 실행하면 각각의 데이터들이 전달됨
        .subscribe { println("# Solar System: $it") }
}