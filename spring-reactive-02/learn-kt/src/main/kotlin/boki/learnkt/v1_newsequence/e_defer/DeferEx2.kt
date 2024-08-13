package boki.learnkt.v1_newsequence.e_defer

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.time.Duration

/**
 * Defer 사용 예제
 * - switchIfEmpty()에 파라미터로 입력되는 Sequence는 Upstream에서 emit되는 데이터가 없을경우 Downstream에 emit한다
 * - 하지만 파라미터로 입력된 sayDefault()는 switchIfEmpty()가 선언된 시점에 이미 호출이 되기때문에
 * - downstream에 데이터를 emit 하지는 않지만 불필요한 메서드 호출이 발생한다
 */
fun main() {
    Logger.info("# Start")

    Mono
        .just("Hello")
        .delayElement(Duration.ofSeconds(2))
        // .switchIfEmpty(sayDefault()) // 함수 방식으로 하면 Say Hi가 출력됨
        .switchIfEmpty { sayDefault() } // 람다 방식으로 하면 Say Hi가 출력되지 않음(지연호출 기능)
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2500)
}

private fun sayDefault(): Mono<String> {
    Logger.info("# Say Hi")
    return Mono.just("Hi")
}