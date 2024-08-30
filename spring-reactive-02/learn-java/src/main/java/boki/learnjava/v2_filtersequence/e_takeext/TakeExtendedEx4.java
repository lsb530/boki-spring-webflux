package boki.learnjava.v2_filtersequence.e_takeext;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * takeWhile 기본 예제
 *  - 파라미터로 입력된 Predicate이 true인 동안 emit된 데이터만 Downstream에 emit
 */
public class TakeExtendedEx4 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .takeWhile(tuple -> tuple.getT2() < 10_000_000)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
