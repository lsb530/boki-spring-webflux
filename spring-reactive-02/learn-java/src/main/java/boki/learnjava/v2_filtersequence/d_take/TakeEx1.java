package boki.learnjava.v2_filtersequence.d_take;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * take 기본 개념 예제
 *  - 파라미터로 입력한 숫자만큼만 Downstream으로 emit
 */
public class TakeEx1 {
    public static void main(String[] args) {
        Flux
            .interval(Duration.ofSeconds(1))
            .doOnNext(Logger::doOnNext)
            .take(3)
            .subscribe(Logger::onNext);

        TimeUtils.sleep(5000L);
    }
}
