package boki.learnkt.v8_multicast.a_publish

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.ConnectableFlux
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * publish() 활용 예제
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - connect()가 호출 되기 전 까지는 데이터를 emit하지 않는다.
 */
fun main() {
    checkAudienceNumbers()

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 1 is watching $it") }
    checkedAudienceNumbers++

    checkAudienceNumbers()

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 2 is watching $it") }
    checkedAudienceNumbers++

    checkAudienceNumbers()

    TimeUtils.sleep(500L)
    publisher.subscribe { Logger.info("# audience 3 is watching $it") }
    checkedAudienceNumbers++

    TimeUtils.sleep(1000L)
}

fun checkAudienceNumbers() {
    if (checkedAudienceNumbers >= 2) {
        publisher.connect()
    }
}

val publisher: ConnectableFlux<String> = Flux
    .just("Concert part1", "Concert part2", "Concert part3")
    .delayElements(Duration.ofMillis(300L))
    .publish()
var checkedAudienceNumbers = 0