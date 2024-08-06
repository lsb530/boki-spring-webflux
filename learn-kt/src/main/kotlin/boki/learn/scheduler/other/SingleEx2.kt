package boki.learn.scheduler.other

import boki.learn.util.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Schedulers.single()을 적용 후,
 * 첫번째 Schedulers.single()에서 할당 된 쓰레드를 재사용 한다.
 */
fun main() {
    doTask("task1")
        .subscribe { Logger.onNext(it) }

    doTask("task2")
        .subscribe { Logger.onNext(it) }

    Thread.sleep(200L)
}

private fun doTask(taskName: String): Flux<Int> {
    return Flux.fromArray(arrayOf(1, 3, 5, 7))
        .doOnNext { Logger.doOnNext(taskName, "fromArray", it) }
        .publishOn(Schedulers.newSingle("new-single", true))
        .filter { it > 3 }
        .doOnNext { Logger.doOnNext(taskName, "filter", it) }
        .map { it * 10 }
        .doOnNext { Logger.doOnNext(taskName, "map", it) }
}