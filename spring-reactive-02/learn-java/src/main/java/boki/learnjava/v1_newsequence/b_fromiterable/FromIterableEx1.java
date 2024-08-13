package boki.learnjava.v1_newsequence.b_fromiterable;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * fromIterable() 사용 예제
 * Iterable 구현 클래스를 파라미터로 입력받아 차례대로 emit
 */
public class FromIterableEx1 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.coinNames)
            .subscribe(Logger::onNext);
    }
}
