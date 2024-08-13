package boki.learnjava.newsequence.range;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * range() 사용 예제
 * 명령형 형식의 for 문을 대체하는 예제
 */
public class RangeEx2 {
    public static void main(String[] args) {
        List<String> coinNames = SampleData.coinNames;

        Flux
            .range(0, coinNames.size())
            .subscribe(index -> Logger.onNext(coinNames.get(index)));
    }
}
