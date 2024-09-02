package boki.learnkt.v3_transformsequence.h_then

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * then 활용 예제
 *  - 1 개의 task가 모두 끝났을 때, Complete Signal을 전달해서 추가 task를 수행하는 예제
 */
fun main() {
    restartApplicationServer()
        .then()
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
            { Logger.onComplete("Send an email to Administrator: Application Server is restarted successfully") }
        )
    TimeUtils.sleep(3000L)
}

private fun restartApplicationServer(): Mono<Unit> {
    return Mono
        .just("Application Server was restarted successfully.")
        .delayElement(Duration.ofSeconds(2))
        .doOnNext { Logger.doOnNext(it) }
        .flatMap { Mono.empty() }
}