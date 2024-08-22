package boki.learnkt.v2_filtersequence.b_skip

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * skip 활용 예제
 *  - 년도별 BTC 최고가 중에서 2000만원 이상이었던 년도 중에서 오래된 년도의 2년을 건너뛴 가격을 표시
 */
fun main() {
    Flux
        .fromIterable(SampleData.btcTopPricesPerYear)
        .filter { it.t2 >= 20_000_000 }
        .skip(2)
        .subscribe { Logger.onNext(it.t1, it.t2) }
}