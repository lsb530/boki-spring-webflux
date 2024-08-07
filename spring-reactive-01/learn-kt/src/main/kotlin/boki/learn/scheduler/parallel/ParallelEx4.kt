package boki.learn.scheduler.parallel

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * - parallel()만 사용할 경우에는 병렬로 작업을 수행하지 않는다.
 * - runOn()을 사용해서 Scheduler를 할당해주어야 병렬로 작업을 수행한다.
 * - CPU 코어 갯수에 의존하지 않고, worker thread를 강제 할당한다.
 */
fun main() {
    val log = LoggerFactory.getLogger(Thread.currentThread().stackTrace[1].className)

    Flux.fromArray(arrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17, 19))
        .parallel(4)
        .runOn(Schedulers.parallel())
        .subscribe { log.info("# onNext(): $it") }

    Thread.sleep(100L)
}