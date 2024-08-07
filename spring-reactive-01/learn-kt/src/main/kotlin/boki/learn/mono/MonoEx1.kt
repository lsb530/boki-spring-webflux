package boki.learn.mono

import reactor.core.publisher.Mono

fun main() {
    Mono.just("Hello Reactor!") // just operator
        .subscribe { println("# emitted data: $it") } // down stream
}