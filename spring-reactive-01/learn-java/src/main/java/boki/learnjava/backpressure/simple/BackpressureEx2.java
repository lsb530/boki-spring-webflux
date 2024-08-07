package boki.learnjava.backpressure.simple;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 갯수를 조절하는 Backpressure 예제
 */
public class BackpressureEx2 {
    public static int count = 0;
    public static void main(String[] args) {
        Flux.range(1, 5)
            .doOnNext(Logger::doOnNext)
            .doOnRequest(Logger::doOnRequest)
            .subscribe(new BaseSubscriber<>() {
                // 데이터의 요청 개수 지정(2)
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(2);
                }

                // 에밋된 데이터를 전달받아서 처리
                @Override
                protected void hookOnNext(Integer value) {
                    count++;
                    Logger.onNext(value);
                    if (count == 2) {
                        TimeUtils.sleep(2000L);
                        request(2);
                        count = 0;
                    }
                }
            });

    }
}
