package boki.learnkt.v7_split.c_groupby

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.text.DecimalFormat

/**
 * groupBy 활용 예제
 * -저자별로 그룹화 한 도서가 모두 판매 되었을 때의 총 인세 수익을 계산한다.
 */
fun main() {
    Flux
        .fromIterable(SampleData.books)
        .groupBy { book -> book.authorName }
        .flatMap { groupedFlux ->
            Mono
                .just(groupedFlux.key())
                .zipWith(
                    groupedFlux
                        .map { it.price * it.stockQuantity * 0.1 }
                        .reduce { t, u -> t + u }
                ) { authorName, sumRoyalty ->
                    "$authorName's royalty: ${DecimalFormat.getInstance().format(sumRoyalty.toInt())}"
                }
        }
        .subscribe { Logger.onNext(it) }
}