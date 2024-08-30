package boki.learnjava.v2_filtersequence.e_takeext;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * takeUntil 기본 예제
 *  - 파라미터로 입력되는 Predicate에 true가 될때까지 데이터를 emit
 *  - emit된 데이터에는 Predicate이 true로 match되는 데이터가 포함
 */
public class TakeExtendedEx2 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .takeUntil(tuple -> tuple.getT2() > 10_000_000) // 마지막 데이터도 전달됨
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
