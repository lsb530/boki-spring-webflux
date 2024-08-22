package boki.learnkt.v2_filtersequence.c_skipext

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터를 건너뜀
 */
fun main() {
    Flux
        .interval(Duration.ofSeconds(1))
        .skipUntilOther(doSomeTask())
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(4000L)
}

fun doSomeTask(): Publisher<*> {
    return Mono.delay(Duration.ofMillis(2500))
}