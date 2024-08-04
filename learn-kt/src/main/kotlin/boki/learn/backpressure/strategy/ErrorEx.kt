package boki.learn.backpressure.strategy

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Error 전략을 적용하는 예제
 * - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우, Exception을 발생 시키는 전략
 */
fun main() {
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureError()
        .doOnNext { println("doOnNext $it") }
        .publishOn(Schedulers.parallel())
        .subscribe(
            { data: Long ->
                Thread.sleep(5L)
                println("onNext $data")
            },
            { error: Throwable -> println("onError $error") })

    Thread.sleep(2000L)
}