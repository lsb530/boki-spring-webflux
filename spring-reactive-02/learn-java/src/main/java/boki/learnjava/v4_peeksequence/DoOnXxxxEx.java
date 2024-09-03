package boki.learnjava.v4_peeksequence;

import boki.learnjava.utils.Logger;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * DoOnXXXX() Operator의 기본 개념 예제
 */
public class DoOnXxxxEx {
    public static void main(String[] args) {
        Flux
            .range(1, 5)
            .doFinally(signalType -> Logger.info("doFinally() > range")) // 6
            .doOnNext(data -> Logger.doOnNext("range", data)) // 4
            .doOnRequest(n -> Logger.info("doOnRequest > range: {}", 1)) // 3
            .doOnSubscribe(subscription -> Logger.info("doOnSubscribe() > range")) // 2
            .doFirst(() -> Logger.info("doFirst() > range")) // 1
            .doOnComplete(() -> Logger.info("doOnComplete() > range")) // 5

            .filter(num -> num % 2 == 1)
            .doOnNext(data -> Logger.doOnNext("filter", data)) // 4
            .doOnRequest(n -> Logger.info("doOnRequest > filter: {}", 1)) // 3
            .doOnSubscribe(subscription -> Logger.info("doOnSubscribe() > filter")) // 2
            .doFinally(signalType -> Logger.info("doFinally() > filter")) // 6
            .doOnComplete(() -> Logger.info("doOnComplete() > filter")) // 5
            .doFirst(() -> Logger.info("doFirst() > filter")) // 1

            .subscribe(new BaseSubscriber<>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    request(1);
                }

                @Override
                protected void hookOnNext(Integer value) {
                    Logger.info("# hookOnNext: {}", value);
                    request(1);
                }
            });
    }
}
