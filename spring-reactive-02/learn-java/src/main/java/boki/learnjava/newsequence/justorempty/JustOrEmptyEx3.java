package boki.learnjava.newsequence.justorempty;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * justOrEmpty() 사용 예
 * 인자로 Optional.isPresent()가 true가 아니라면, onNext emit 없이 onComplete만 emit
 */
public class JustOrEmptyEx3 {
    public static void main(String[] args) {
        Mono
            .justOrEmpty(Optional.ofNullable(null))
            .log()
            .subscribe(Logger::onNext);
    }
}
