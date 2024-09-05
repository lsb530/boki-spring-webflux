package boki.learnkt.v8_multicast.c_refCount

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * refCount() Operator
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - 파라미터로 입력한 숫자만큼의 구독이 발생하는 시점에 connect()가 자동으로 호출된다.
 *  - 파라미터로 입력한 숫자만큼의 구독이 취소되면 Upstream 소스와의 연결을 해제한다.
 * - 시퀀스 자체가 종료된다는 의미는 아님!!(어떻게보면 콜드 시퀀스하고 비슷하게 동작)
 */
fun main() {
    val publisher = Flux
        .interval(Duration.ofMillis(500))
        // .publish().autoConnect(1)
        .publish().refCount(1)

    val disposable = publisher.subscribe { Logger.info("# subscriber 1: $it") }
    TimeUtils.sleep(2100L)

    // 구독이 해제 되었다면 upstream과의 연결이 해제돼서 데이터가 초기화되어야함(다시 0부터)
    disposable.dispose()

    publisher.subscribe { Logger.info("# subscriber 2: $it") }
    TimeUtils.sleep(2500L)
}