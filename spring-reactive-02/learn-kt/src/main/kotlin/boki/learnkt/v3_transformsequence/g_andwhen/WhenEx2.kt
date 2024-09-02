package boki.learnkt.v3_transformsequence.g_andwhen

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * when 활용 예제
 *  - 1개 이상의 task가 모두 끝났을 때, Complete Signal을 전달해서 추가 task를 수행하는 예제
 */
fun main() {
    Mono.`when`(restartApplicationServer(), restartDBServer(), restartStorageServer())
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

private fun restartDBServer(): Mono<String> {
    return Mono
        .just<String>("DB Server was restarted successfully.")
        .delayElement(Duration.ofSeconds(4))
        .doOnNext { Logger.doOnNext(it) }
}

private fun restartStorageServer(): Mono<String> {
    return Mono
        .just<String>("Storage Server was restarted successfully.")
        .delayElement(Duration.ofSeconds(3))
        .doOnNext { Logger.doOnNext(it) }
}