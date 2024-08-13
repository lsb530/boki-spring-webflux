package boki.learnjava.v1_newsequence.justorempty;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Mono;

/**
 * justOrEmpty() 사용 예
 * 인자로 null을 입력하면 NPE가 발생하지 않고, onNext emit 없이 onCompete만 emit
 */
public class JustOrEmptyEx2 {
    public static void main(String[] args) {
        Mono
            .justOrEmpty(null)
            .log()
            .subscribe(Logger::onNext);
    }
}
