package boki.learnkt.v1_newsequence.e_defer

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Mono
import java.time.LocalDateTime

/**
 * Defer 사용 예제
 * 실제로 구독이 발생하는 시점에 데이터를 emit
 * 실행해보면 just와 defer의 emit 시간이 다르다(just는 main 메서드의 시작(24초)과 동시에 onNext, defer는 26, 28초에 onNext)
 *
 * 01:15:34.007 [main] INFO boki.learnkt.util.Logger -- # just1 onNext(): 2024-08-14T01:15:31.958760
 * 01:15:34.008 [main] INFO boki.learnkt.util.Logger -- # defer1 onNext(): 2024-08-14T01:15:34.008927
 * 01:15:36.017 [main] INFO boki.learnkt.util.Logger -- # just2 onNext(): 2024-08-14T01:15:31.958760
 * 01:15:36.019 [main] INFO boki.learnkt.util.Logger -- # defer2 onNext(): 2024-08-14T01:15:36.019878
 */
fun main() {
    Logger.info("# Starting")

    val justMono = Mono.just(LocalDateTime.now())
    val deferMono = Mono.defer { Mono.just(LocalDateTime.now()) }

    TimeUtils.sleep(2000)

    justMono.subscribe { Logger.onNext("just1", it) }
    deferMono.subscribe { Logger.onNext("defer1", it) }

    TimeUtils.sleep(2000)

    justMono.subscribe { Logger.onNext("just2", it) }
    deferMono.subscribe { Logger.onNext("defer2", it) }
}