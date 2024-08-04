package boki.learn.backpressure.sink.many

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import reactor.core.publisher.Sinks.many

/**
 * Sinks.Many 예제
 * - replay()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
fun main() {
    // 구독 이후, emit된 데이터 중에서 최신 데이터 2개만 replay한다
    val replaySink = many().replay().limit<Any>(2)
    val fluxView = replaySink.asFlux()

    replaySink.emitNext(1, FAIL_FAST)
    replaySink.emitNext(2, FAIL_FAST)
    replaySink.emitNext(3, FAIL_FAST)

    // 1: 2, 3
    fluxView.subscribe { println("${ConsoleColors.BLUE} # Subscriber1 onNext(): $it ${ConsoleColors.RESET}") }

    replaySink.emitNext(4, FAIL_FAST)

    // 1: 4, 2: 3, 4(두번째 구독이 발생한 시점을 기준으로 가장 최근에 에밋된 데이터2개는 3, 4)
    fluxView.subscribe { println("${ConsoleColors.YELLOW} # Subscriber2 onNext(): $it ${ConsoleColors.RESET}") }
}
