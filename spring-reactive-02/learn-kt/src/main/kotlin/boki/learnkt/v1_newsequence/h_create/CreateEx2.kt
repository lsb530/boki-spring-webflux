package boki.learnkt.v1_newsequence.h_create

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

/**
 * create 개념 이해 예제
 *  - Subscriber의 request와 상관없이 next signal 이벤트를 발생하는 예제
 */
fun main() {
    Logger.info("# start")

    val priceEmitter = CryptoCurrencyPriceEmitter()

    Flux.create { sink: FluxSink<Int> ->
        priceEmitter.setListener(object : CryptoCurrencyPriceListener {
            override fun onPrice(priceList: List<Int>) {
                priceList.forEach { sink.next(it) }
            }

            override fun onComplete() {
                sink.complete()
            }
        })
    }
        .publishOn(Schedulers.parallel())
        .subscribe(
            { Logger.onNext(it) },
            { error -> Logger.onError(error)},
            { Logger.info("# onComplete") }
        )

    TimeUtils.sleep(3000L)

    priceEmitter.flowInto()

    TimeUtils.sleep(2000L)

    priceEmitter.complete()

    TimeUtils.sleep(100L)
}