package boki.learnjava.v1_newsequence.range;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * range() 사용 예제
 *  range()를 사용해서 list의 특정 index에 해당하는 데이터를 조회하는 예제
 */
public class RangeEx3 {
    public static void main(String[] args) {
        Flux
            .range(7, 5)
            .map(SampleData.btcTopPricesPerYear::get)
            .subscribe(tuple -> Logger.onNext(
                String.format("%s's %,d", tuple.getT1(), tuple.getT2())
            ));
    }
}
