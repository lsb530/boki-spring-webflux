package boki.learnjava.v1_newsequence.justorempty;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Mono;

/**
 * just()에 null 값을 입력하면 NullPointerException이 발생
 */
public class JustOrEmptyEx1 {
    public static void main(String[] args) {
        Mono
            .just(null)
            .log()
            .subscribe(Logger::onNext);
    }
}
