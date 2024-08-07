package boki.learnjava.backpressure.simple;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
public class BackpressureEx1 {
    public static void main(String[] args) {
        Flux.range(1, 5)
            .doOnNext(Logger::doOnNext)
            .doOnRequest(Logger::doOnRequest)
            .subscribe(new BaseSubscriber<>() {
                // 데이터의 요청 개수 지정(1)
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                // 에밋된 데이터를 전달받아서 처리
                @Override
                protected void hookOnNext(Integer value) {
                    TimeUtils.sleep(2000L);
                    Logger.onNext(value);
                    request(1);
                }
            });
    }
}
