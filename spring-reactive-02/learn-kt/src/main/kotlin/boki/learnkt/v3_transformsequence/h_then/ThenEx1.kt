package boki.learnkt.v3_transformsequence.h_then

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * then 기본 개념 예제
 *  - Upstream Mono의 sequence가 종료되면, Mono<Void>를 Downstream으로 전달한다.
 */
fun main() {
    Mono
        .just("Hi")
        .delayElement(Duration.ofSeconds(1))
        .doOnNext { Logger.doOnNext(it) }
        .then()
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
            { Logger.onComplete() }
        )

    TimeUtils.sleep(1500L)
}