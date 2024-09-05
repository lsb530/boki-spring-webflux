package boki.learnkt.v8_multicast.d_replay

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * replay() 개념 예제
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - connect()가 호출 되기 전 까지는 데이터를 emit하지 않는다.
 *  - 파라미터로 지정한 개수 만큼 마지막에 emit된 데이터를 캐시한다.
 */
fun main() {
    val connectableReplayFlux = Flux
        .range(1, 5)
        .delayElements(Duration.ofMillis(300L))
        .replay(2)

    TimeUtils.sleep(500L)
    connectableReplayFlux.subscribe { Logger.onNext("# subscriber 1: $it") }

    TimeUtils.sleep(200L)
    connectableReplayFlux.subscribe { Logger.onNext("# subscriber 2: $it") }

    connectableReplayFlux.connect()

    TimeUtils.sleep(1000L)
    // 가장 마지막에 emit된 소스의 개수만큼 한꺼번에 전달받음(replay로 인해) -> 1(x), 2, 3
    connectableReplayFlux.subscribe { Logger.onNext("# subscriber 3: $it") }

    TimeUtils.sleep(2000L)
}