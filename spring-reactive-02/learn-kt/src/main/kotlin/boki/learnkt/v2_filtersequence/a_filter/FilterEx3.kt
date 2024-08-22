package boki.learnkt.v2_filtersequence.a_filter

import boki.learnjava.common.SampleData
import boki.learnkt.common.CoronaVaccineService
import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux

/**
 * 백신의 재고를 기준 이상으로 보유하고 있는 백신만 출력하도록 하는 예제
 * - filterWhen을 사용해서 비동기적으로 필터링을 한다
 */
fun main() {
    Flux
        .fromIterable(SampleData.coronaVaccineNames)
        .filterWhen { CoronaVaccineService.isGreaterThan(it, 3_000_000) }
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(1000)
}