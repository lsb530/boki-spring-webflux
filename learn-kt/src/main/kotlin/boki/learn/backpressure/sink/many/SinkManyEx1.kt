package boki.learn.backpressure.sink.many

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Sinks.*
import reactor.core.publisher.Sinks.EmitFailureHandler.*

/**
 * Sinks.Many 예제
 * - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
 */
fun main() {
    // 단 하나의 Subscriber에게만 데이터를 emit할 수 있다
    val unicastSink = many().unicast().onBackpressureBuffer<Any>()
    val fluxView = unicastSink.asFlux()

    unicastSink.emitNext(1, FAIL_FAST)
    unicastSink.emitNext(2, FAIL_FAST)

    fluxView.subscribe { println("${ConsoleColors.BLUE} # Subscriber1 onNext(): $it ${ConsoleColors.RESET}") }

    unicastSink.emitNext(3, FAIL_FAST)

    // 두 개의 구독은 허용하지 않음
    fluxView.subscribe { println("${ConsoleColors.YELLOW} # Subscriber2 onNext(): $it ${ConsoleColors.RESET}") }
}
