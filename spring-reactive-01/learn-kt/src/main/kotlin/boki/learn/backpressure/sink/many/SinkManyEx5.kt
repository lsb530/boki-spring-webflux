package boki.learn.backpressure.sink.many

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import reactor.core.publisher.Sinks.many

/**
 * Sinks.Many 예제
 * - replay().all()을 사용하여 구독 시점과 상관없이 모든 데이터를 replay한다
 */
fun main() {
    // 구독 시점과 상관없이 emit된 모든 데이터를 replay한다
    val replaySink = many().replay().all<Any>()
    val fluxView = replaySink.asFlux()

    replaySink.emitNext(1, FAIL_FAST)
    replaySink.emitNext(2, FAIL_FAST)
    replaySink.emitNext(3, FAIL_FAST)

    fluxView.subscribe { println("${ConsoleColors.BLUE} # Subscriber1 onNext(): $it ${ConsoleColors.RESET}") }
    fluxView.subscribe { println("${ConsoleColors.YELLOW} # Subscriber2 onNext(): $it ${ConsoleColors.RESET}") }

    Thread.sleep(1000L)
}