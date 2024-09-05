package boki.learnkt.v7_split.c_groupby

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * groupBy(keyMapper, valueMapper) 기본 개념 예제
 * - emit되는 데이터를 keyMapper로 생성한 key를 기준으로 그룹화 한 후에
 * valueMapper를 통해 그룹화 된 데이터를 다른 형태로 가공 처리한 후, Downstream으로 emit한다.
 */
fun main() {
    Flux
        .fromIterable(SampleData.books)
        .groupBy(
            { it.authorName },
            { "${it.bookName}(${it.authorName})" }
        )
        .flatMap { groupedFlux -> groupedFlux.collectList() }
        .subscribe { Logger.onNext(it) }
}