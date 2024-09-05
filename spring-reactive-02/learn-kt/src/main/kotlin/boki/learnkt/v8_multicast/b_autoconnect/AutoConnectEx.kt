package boki.learnkt.v8_multicast.b_autoconnect

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * autoConnect() 기본 개념 예제
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - 파라미터로 입력한 숫자 만큼의 구독이 발생하는 시점에 connect()가 자동으로 호출된다.
 */
fun main() {
    val publisher = Flux
        .just("Concert part1", "Concert part2", "Concert part3")
        .delayElements(Duration.ofMillis(300L))
        .publish()
        .autoConnect(2)

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 1 is watching $it") }

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 2 is watching $it") }

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 3 is watching $it") }

    TimeUtils.sleep(1000L)
}