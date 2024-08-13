package boki.learnkt.v1_newsequence.h_create

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

/**
 * create 개념 이해 예제
 * - Subscriber 가 request 할 경우에 next signal 이벤트를 발생하는 예제
 * - generate operator 와 달리 한번에 여러 건의 next signal 이벤트를 발생 시킬 수 있다.
 */
fun main() {
    var size = 0
    var count = -1
    val dataSource: List<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    Logger.info("# start")

    Flux.create { emitter: FluxSink<Int> ->
        emitter.onRequest { n ->
            TimeUtils.sleep(1000L)
            for (i in 0 until n) {
                if (count >= 9) {
                    emitter.complete()
                    break
                }
                else {
                    count++
                    emitter.next(dataSource[count])
                }
            }
        }

        emitter.onDispose { Logger.info("# clean up") }
    }
        .subscribe(object : BaseSubscriber<Int>() {
            override fun hookOnSubscribe(subscription: Subscription) {
                request(2)
            }

            override fun hookOnNext(value: Int) {
                size++
                Logger.onNext(value)
                if (size == 2) {
                    request(2)
                    size = 0
                }
            }

            override fun hookOnComplete() {
                Logger.onComplete("# onComplete")
            }
        }
        )
}