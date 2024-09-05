package boki.learnkt.v7_split.c_groupby

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * groupBy(keyMapper) 기본 개념 예제
 *  - emit되는 데이터를 keyMapper로 생성한 key를 기준으로 그룹화 한 GroupedFlux를 리턴한다.
 */
fun main() {
    Flux
        .fromIterable(SampleData.books)
        .groupBy { it.authorName }
        .flatMap { book -> book.map { "${it.bookName}(${it.authorName})" }.collectList() }
        .subscribe { Logger.onNext(it) }
}