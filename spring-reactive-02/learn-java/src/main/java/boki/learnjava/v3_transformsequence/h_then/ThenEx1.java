package boki.learnjava.v3_transformsequence.h_then;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * then 기본 개념 예제
 *  - Upstream Mono의 sequence가 종료되면, Mono<Void>를 Downstream으로 전달한다.
 */
public class ThenEx1 {
    public static void main(String[] args) {
        Mono
            .just("Hi")
            .delayElement(Duration.ofSeconds(1))
            .doOnNext(Logger::doOnNext)
            .then()
            .subscribe(
                Logger::onNext,
                Logger::onError,
                Logger::onComplete
            );

        TimeUtils.sleep(1500);
    }
}
