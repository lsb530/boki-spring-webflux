package boki.learn.stepverifier

import reactor.core.publisher.Mono

class PublisherProbeExample {
    companion object {
        fun processWith(main: Mono<String>, standby: Mono<String>?): Mono<String> {
            return main
                .flatMap { massage: String ->
                    Mono.just(massage)
                }
                .switchIfEmpty(standby!!)
        }

        fun useMainPower(): Mono<String> {
            return Mono.empty()
        }

        fun useStandbyPower(): Mono<String> {
            return Mono.just("# use Standby Power")
        }
    }
}