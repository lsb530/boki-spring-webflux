package boki.learnjava.v1_newsequence.fromiterable;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * fromIterable() 사용 예제
 * Iterable 구현 클래스를 파라미터로 입력받아 차례대로 emit
 */
public class FromIterableEx2 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.coins)
            .subscribe(coin -> Logger.onNext(
                String.format("coin 명: %s, 현재가: %d", coin.getT1(), coin.getT2())
            ));
    }
}
