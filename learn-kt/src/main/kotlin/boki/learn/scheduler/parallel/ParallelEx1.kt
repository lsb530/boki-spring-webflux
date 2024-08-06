package boki.learn.scheduler.parallel

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * parallel()만 사용할 경우에는 병렬로 작업을 수행하지 않는다.
 */
fun main() {
    val log = LoggerFactory.getLogger(Thread.currentThread().stackTrace[1].className)

    Flux.fromArray(arrayOf(1, 3, 5, 7, 9, 11, 13, 15))
        .parallel()
        .subscribe { log.info("# onNext(): $it") }
}