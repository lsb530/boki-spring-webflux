package boki.learn.stepverifier

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

class BackpressureExample {
    companion object {
        fun generateNumberByErrorStrategy(): Flux<Int?> {
            return Flux
                .create({ emitter: FluxSink<Int?> ->
                    for (i in 1..100) {
                        emitter.next(i)
                    }
                    emitter.complete()
                }, FluxSink.OverflowStrategy.ERROR)
        }

        fun generateNumberByDropStrategy(): Flux<Int?> {
            return Flux
                .create({ emitter: FluxSink<Int?> ->
                    for (i in 1..100) {
                        emitter.next(i)
                    }
                    emitter.complete()
                }, FluxSink.OverflowStrategy.DROP)
        }
    }
}