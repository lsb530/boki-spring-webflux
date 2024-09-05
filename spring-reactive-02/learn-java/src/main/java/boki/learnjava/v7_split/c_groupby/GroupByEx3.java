package boki.learnjava.v7_split.c_groupby;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.NumberFormat;

/**
 * groupBy 활용 예제
 * -저자별로 그룹화 한 도서가 모두 판매 되었을 때의 총 인세 수익을 계산한다.
 */
public class GroupByEx3 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.books)
            .groupBy(book -> book.getAuthorName())
            .flatMap(groupedFlux ->
                Mono
                    .just(groupedFlux.key())
                    .zipWith(
                        groupedFlux
                            .map(book -> (int) (book.getPrice() * book.getStockQuantity() * 0.1))
                            .reduce((y1, y2) -> y1 + y2),
                        (authorName, sumRoyalty) -> authorName + "'s royalty: " + NumberFormat.getNumberInstance().format(sumRoyalty))
            )
            .subscribe(Logger::onNext);
    }
}
