package boki.learnkt.v5_delayandtimeout

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * timeout 기본 개념 예제
 *  - 파라미터로 입력한 시간 안에 Upstream에서 데이터가 emit 되지 않으면 TimeoutException을 발생시킨다.
 */
fun main() {
    requestToServer()
        .timeout(Duration.ofSeconds(2))
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) },
        )

    TimeUtils.sleep(3500)
}

private fun requestToServer(): Mono<String> {
    return Mono.just("complete to process request from client successfully")
        .delayElement(Duration.ofSeconds(3))
}