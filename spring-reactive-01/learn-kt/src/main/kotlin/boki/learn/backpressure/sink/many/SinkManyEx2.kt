package boki.learn.backpressure.sink.many

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import reactor.core.publisher.Sinks.many

/**
 * Sinks.Many 예제
 * - multicast()를 사용해서 하나 이상의 Subscriber에게 데이터를 emit하는 예제
 */
fun main() {
    // 하나 이상의 Subscriber에게만 데이터를 emit할 수 있다
    val unicastSink = many().multicast().onBackpressureBuffer<Any>()
    val fluxView = unicastSink.asFlux()

    unicastSink.emitNext(1, FAIL_FAST)
    unicastSink.emitNext(2, FAIL_FAST)

    fluxView.subscribe { println("${ConsoleColors.BLUE} # Subscriber1 onNext(): $it ${ConsoleColors.RESET}") }
    fluxView.subscribe { println("${ConsoleColors.YELLOW} # Subscriber2 onNext(): $it ${ConsoleColors.RESET}") }

    // Sub2는 3만 받는다. Hot Sequence/Warm up 방식으로 동작하기 때문
    unicastSink.emitNext(3, FAIL_FAST)
}
