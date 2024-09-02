package boki.learnkt.v3_transformsequence.g_andwhen

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * when 기본 개념 예제
 *  - 파라미터로 입력된 Publisher들이 종료할 때 까지 대기한 후, Mono<Void>를 반환한다.
 */
fun main() {
    Mono
        .`when`(
            Flux
                .just("Hi", "Tom")
                .delayElements(Duration.ofSeconds(2))
                .doOnNext { Logger.doOnNext(it) },
            Flux
                .just("Hello", "David")
                .delayElements(Duration.ofSeconds(1))
                .doOnNext { Logger.doOnNext(it) }
        )
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
            { Logger.onComplete() }
        )

    TimeUtils.sleep(5000L)
}