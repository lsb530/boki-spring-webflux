package boki.learnjava.newsequence.fromstream;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * fromStream() 사용 예제
 * Stream을 return하는 supplier를 파라미터로 입력받아서
 *  return되는 Stream에 포함된 데이터를 차례대로 emit
 */
public class FromStreamEx2 {
    public static void main(String[] args) {
        Flux
            .fromStream(SampleData.coinNames.stream())
            .filter(coin -> coin.equals("BTC") || coin.equals("ETH"))
            .subscribe(Logger::onNext);
    }
}
