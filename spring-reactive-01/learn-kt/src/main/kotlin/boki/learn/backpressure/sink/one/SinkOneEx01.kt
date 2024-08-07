package boki.learn.backpressure.sink.one

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler

/**
 * Sinks.One 예제
 * - 한 건의 데이터만 emit 하는 예제
 */
fun main() {
    // emit 된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop 됨.
    val sinkOne = Sinks.one<String>()
    val mono = sinkOne.asMono()

    sinkOne.emitValue("Hello Reactor", EmitFailureHandler.FAIL_FAST)

    mono.subscribe { println("${ConsoleColors.BLUE} # Subscriber1 onNext(): $it ${ConsoleColors.RESET}") }
    mono.subscribe { println("${ConsoleColors.YELLOW} # Subscriber2 onNext(): $it ${ConsoleColors.RESET}") }
}