package boki.learnkt.common

import boki.learnjava.common.SampleData
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.util.function.Tuple2

object CoronaVaccineService {
    var vaccineMap: Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Int>> = getCoronaVaccinesMap()

    fun isGreaterThan(coronaVaccine: SampleData.CoronaVaccine, amount: Int): Mono<Boolean> {
        return Mono
            .just(vaccineMap[coronaVaccine]!!.t2 > amount)
            .publishOn(Schedulers.parallel())
    }

    private fun getCoronaVaccinesMap(): Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Int>> {
        return SampleData.getCoronaVaccinesMap()
    }
}