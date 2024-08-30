package boki.learnjava.v2_filtersequence.e_takeext;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * takeLast 기본 예제
 *  emit된 데이터중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit된 데이터만 가져옴
 */
public class TakeExtendedEx1 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .takeLast(2)
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
