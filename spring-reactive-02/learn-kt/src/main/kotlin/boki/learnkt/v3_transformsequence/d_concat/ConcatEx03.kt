package boki.learnkt.v3_transformsequence.d_concat

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

/**
 * Concat 활용 예제
 *  - 유형별 코로나 백신 list 를 concat 하는 예제
 */
fun main() {
    Flux
        .concat(
            Flux.fromIterable(getViralVectorVaccines()),
            Flux.fromIterable(getmRNAVaccines()),
            Flux.fromIterable(getSubunitVaccines())
        )
        .subscribe { Logger.onNext(it) }
}

private fun getViralVectorVaccines(): List<Tuple2<SampleData.CoronaVaccine, Int>> {
    return SampleData.viralVectorVaccines
}

private fun getmRNAVaccines(): List<Tuple2<SampleData.CoronaVaccine, Int>> {
    return SampleData.mRNAVaccines
}

private fun getSubunitVaccines(): List<Tuple2<SampleData.CoronaVaccine, Int>> {
    return SampleData.subunitVaccines
}