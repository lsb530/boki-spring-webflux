package boki.learn.backpressure.sink.simple

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

/**
 * create() Operator를 사용하는 예제
 * - 일반적으로 Publisher의 데이터 생성을 단일 쓰레드에서 진행한다. 멀티 쓰레드에서도 가능
 * - 데이터 emit은 create Operator 내부에서 가능.
 * - Backpressure 적용 가능
 */
fun main() {
    val tasks = 6

    Flux.create { sink: FluxSink<String> ->
        IntStream
            .range(1, tasks)
            .forEach { n: Int -> sink.next(doTask(n)) }
    }
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext { println("# create(): $it") }
        .publishOn(Schedulers.parallel())
        .map { "$it success!" }
        .doOnNext { println("# map(): $it") }
        .publishOn(Schedulers.parallel())
        .subscribe { println("# onNext: $it") }
    Thread.sleep(500L)
}

private fun doTask(taskNumber: Int): String {
    // now tasking.
    // complete to task.
    return "task $taskNumber result"
}