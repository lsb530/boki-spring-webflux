package boki.learnkt.v3_transformsequence.c_flatmapext

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * flatMapSequential 기본 개념 예제
 *  - 비동기적으로 동작할 경우에도 emit 되는 순서를 보장한다.
 */
fun main() {
   Flux
       .range(2, 8)
       .flatMapSequential { dan ->
           Flux.range(1, 9)
               .publishOn(Schedulers.parallel())
               .map { n -> "$dan * $n = ${dan * n}" }
       }
       .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(200L)
}