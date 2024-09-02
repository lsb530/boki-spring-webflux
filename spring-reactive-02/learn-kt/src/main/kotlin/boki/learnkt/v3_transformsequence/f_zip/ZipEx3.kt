package boki.learnkt.v3_transformsequence.f_zip

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import reactor.util.function.Tuple3

/**
 * zip 활용 예제
 * - 수도권의 시간별 코로나 확진자 수를 집계하는 예제
 */
fun main() {
    getInfectedPersonsPerHour(10, 21)
        .subscribe { t3  ->
            val sum = t3.t1.t2 + t3.t2.t2 + t3.t3.t2
            Logger.onNext(t3.t1.t1, sum)
        }
    TimeUtils.sleep(6000L)
}

private fun getInfectedPersonsPerHour(start: Int, end: Int): Flux<Tuple3<Tuple2<Int, Int>, Tuple2<Int, Int>, Tuple2<Int, Int>>> {
    return Flux.zip(
        Flux.fromIterable(SampleData.seoulInfected)
            .filter { it.t1 in start..end },
        Flux.fromIterable(SampleData.incheonInfected)
            .filter { it.t1 in start..end },
        Flux.fromIterable(SampleData.suwonInfected)
            .filter { it.t1 in start..end }
    )
}