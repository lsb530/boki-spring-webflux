package boki.learn.backpressure.sink.simple

import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

/**
 * Sinks를 사용하는 예제
 *  - Publisher의 데이터 생성을 멀티 쓰레드에서 진행해도 Thread safe 하다.
 */
fun main() {
    val tasks = 6

    val unicastSink = Sinks.many().unicast().onBackpressureBuffer<String>()
    val fluxView = unicastSink.asFlux()
    IntStream
        .range(1, tasks)
        .forEach { n ->
            try {
                Thread {
                    unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST)
                    println("# emitted : $n")
                }.start()
                Thread.sleep(100L)
            } catch (e: InterruptedException) { /* */ }
        }

    fluxView
        .publishOn(Schedulers.parallel())
        .map { result: String -> "$result success!" }
        .doOnNext { println("# map(): $it") }
        .publishOn(Schedulers.parallel())
        .subscribe { println("# onNext: $it") }

    Thread.sleep(200L)
}

private fun doTask(taskNumber: Int): String {
    // now tasking.
    // complete to task.
    return "task $taskNumber result"
}