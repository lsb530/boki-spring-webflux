package boki.learn.scheduler.operator

import boki.learn.util.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * Sequence의 Operator 체인에서 최초의 쓰레드는 subscribe()가
 * 호출되는 scope에 있는 쓰레드이다.
 */
fun main() {
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .filter { it > 3 }
        .map { it * 10 }
        .subscribe { Logger.onNext(it) }
}