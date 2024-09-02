package boki.learnkt.v3_transformsequence.g_andwhen

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * and 활용 예제
 *  - 2 개의 task가 모두 끝났을 때, Complete Signal을 전달해서 추가 task를 수행하는 예제
 */
fun main() {
    restartApplicationServer()
        .and(restartDBServer()) // Mono<Void> emit -> complete 시그널만 호출
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
            { Logger.onComplete("Send an email to Administrator: All Servers are restarted successfully") }
        )

    TimeUtils.sleep(6000L)
}

private fun restartApplicationServer(): Mono<String> {
    return Mono
        .just<String>("Application Server was restarted successfully.")
        .delayElement(Duration.ofSeconds(2))
        .doOnNext { Logger.doOnNext(it) }
}

private fun restartDBServer(): Publisher<String> {
    return Mono
        .just<String>("DB Server was restarted successfully.")
        .delayElement(Duration.ofSeconds(4))
        .doOnNext { Logger.doOnNext(it) }
}