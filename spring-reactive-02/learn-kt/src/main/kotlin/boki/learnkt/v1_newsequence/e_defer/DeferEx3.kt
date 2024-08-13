package boki.learnkt.v1_newsequence.e_defer

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력되는 Sequence는 Upstream에서 emit되는 데이터가 없을경우 Downstream에 emit한다
 * - 불필요한 호출을 방지하기 위해 실제 필요한 시점에 데이터를 emit하도록 defer()를 사용한다
 */
fun main() {
    Logger.info("# Start")

    Mono
        .just("Hello")
        .delayElement(Duration.ofSeconds(2))
        .switchIfEmpty(Mono.defer { sayDefault() }) // 1 - 이 방식으로 선택!!
        // .switchIfEmpty { Mono.defer { sayDefault() } } // 2 - 이중람다는 좋지 않음
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2500)
}

private fun sayDefault(): Mono<String> {
    Logger.info("# Say Hi")
    return Mono.just("Hi")
}