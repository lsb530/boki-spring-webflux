package boki.learn.backpressure.buffer

import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Buffer DROP_LATEST 전략을 적용하는 예제
 *  - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우,
 *    버퍼 안에 있는 데이터 중에서 가장 최근에(나중에) 버퍼로 들어온 데이터부터 Drop 시키는 전략
 */
fun main() {
    Flux
        .interval(Duration.ofMillis(300L))
        .doOnNext { println("# emitted by original Flux: $it") }
        .onBackpressureBuffer(
            2,
            { dropped: Long -> println("# Overflow & dropped: $dropped") },
            BufferOverflowStrategy.DROP_LATEST
        )
        .doOnNext { println("# emitted by Buffer: $it") }
        .publishOn(Schedulers.parallel(), false, 1)
        .subscribe(
            { data: Long ->
                Thread.sleep(1000L)
                println("onNext $data")
            },
            { error: Throwable -> println("onError $error") })

    Thread.sleep(3000L)
}