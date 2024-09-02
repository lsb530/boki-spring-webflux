package boki.learnkt.v3_transformsequence.c_flatmapext

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.util.function.Tuple2

/**
 * flatMapIterable 기본 개념 예제
 *  - Iterable로 emit 된 데이터를 순차적으로 평탄화 한다.
 *  - 유형별 코로나 백신 list 를 평탄화 하는 예제
 */
fun main() {
    Flux.just(getViralVectorVaccines(), getmRNAVaccines(), getSubunitVaccines())
        // .map { it }
        .flatMapIterable { it }
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