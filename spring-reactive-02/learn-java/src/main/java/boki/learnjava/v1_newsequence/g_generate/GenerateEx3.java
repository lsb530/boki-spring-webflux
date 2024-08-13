package boki.learnjava.v1_newsequence.g_generate;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Map;

/**
 * generate 개념 이해 예제
 * - 2016년도 이후의 해당 연도(2017 - 2021년)의 BTC 최고가 금액을 출력
 */
public class GenerateEx3 {
    public static void main(String[] args) {
        Map<Integer, Tuple2<Integer, Long>> map = SampleData.getBtcTopPricesPerYearMap();

        Flux
            .generate(() -> 2017, (state, sink) -> {
                if (state > 2021) {
                    sink.complete();
                }
                else {
                    sink.next(map.get(state));
                }
                return ++state;
            })
            .subscribe(Logger::onNext);
    }
}
