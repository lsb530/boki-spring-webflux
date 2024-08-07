package boki.learn.debug.mode

import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks

/**
 * onOperatorDebug() Hook 메서드를 이용한 Debug mode
 * - 애플리케이션 전체에서 global 하게 동작한다.
 */
fun main() {
    Hooks.onOperatorDebug()

    Flux.just(2, 4, 6, 8)
        .zipWith(Flux.just(1, 2, 3, 0)) { x, y -> x / y }
        .subscribe(
            { data -> Logger.onNext(data) },
            { error -> Logger.onError(error) }
        )
}