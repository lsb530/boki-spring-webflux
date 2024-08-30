package boki.learnjava.v2_filtersequence.f_next;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * next 기본 예제
 *  - emit된 데이터중에서 첫번째 데이터만 Downstream으로 emit하고 구독을 취소
 */
public class NextEx1 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.btcTopPricesPerYear)
            .doOnNext(Logger::doOnNext)
            .filter(tuple -> tuple.getT1() == 2015)
            .next()
            .subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
    }
}
