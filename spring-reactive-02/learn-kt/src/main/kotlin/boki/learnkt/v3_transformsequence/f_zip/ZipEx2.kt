package boki.learnkt.v3_transformsequence.f_zip

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * zip 기본 개념 예제
 *  - 파라미터로 입력된 Publisher Sequence에서 emit된 데이터를 결합한다.
 *  - combinator를 추가해서 결합된 데이터를 활용해서 변환할 수 있다.
 */
fun main() {
    Flux
        .zip(
            Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
            Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L))
        ) { n1: Int, n2: Int -> n1 * n2 }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2500L)
}