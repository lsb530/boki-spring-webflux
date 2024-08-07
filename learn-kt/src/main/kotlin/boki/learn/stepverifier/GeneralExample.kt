package boki.learn.stepverifier

import reactor.core.publisher.Flux

class GeneralExample {
    companion object {
        fun sayHelloReactor(): Flux<String> {
            return Flux
                .just("Hello", "Reactor")
        }

        fun divideByTwo(source: Flux<Int>): Flux<Int> {
            return source
                .zipWith(Flux.just(2, 2, 2, 2, 2))
                { x: Int, y: Int -> x / y }
        }

        fun occurError(source: Flux<Int>): Flux<Int> {
            return source
                .zipWith(Flux.just(2, 2, 2, 2, 0))
                { x: Int, y: Int -> x / y }
        }

        fun takeNumber(source: Flux<Int?>, n: Long): Flux<Int?> {
            return source
                .take(n)
        }
    }
}