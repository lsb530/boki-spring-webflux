package boki.learn.debug.mode

import boki.learn.util.Logger
import reactor.core.publisher.Flux

/**
 * Non-Debug mode
 */
fun main() {
    Flux.just(2, 4, 6, 8)
        .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )
}