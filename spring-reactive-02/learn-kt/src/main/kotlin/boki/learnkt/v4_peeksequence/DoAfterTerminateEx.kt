package boki.learnkt.v4_peeksequence

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * doOnTerminate()와 doAfterTerminate()의 차이점을 이해하기 위한 예제
 *  - doOnTerminate()는 upstream operator가 종료할 때 호출된다.
 *  - doAfterTerminate는 전체 Sequence가 종료할 때 Downstream에서 Upstream으로 전파(propagation)되면서 호출된다.
 */
fun main() {
    Flux
        .just("HI", "HELLO")
        .filter { it == "HI" }
        .doOnTerminate { Logger.doOnTerminate("filter") }
        .doAfterTerminate { Logger.doAfterTerminate("filter") }
        .map { it.lowercase() }
        .doOnTerminate { Logger.doOnTerminate("map") }
        .doAfterTerminate { Logger.doAfterTerminate("map") }
        .subscribe (
            { Logger.onNext(it) },
            { },
            { Logger.doOnComplete() }
        )
}