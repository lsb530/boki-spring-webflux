package boki.learnjava.scheduler.operator;

import boki.learnjava.util.Logger;
import reactor.core.publisher.Flux;

/**
 * Sequence의 Operator 체인에서 최초의 쓰레드는 subscribe()가
 * 호출되는 scope에 있는 쓰레드이다.
 */
public class OpEx1 {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7}) // main
            .filter(data -> data > 3)             // main
            .map(data -> data * 10)               // main
            .subscribe(Logger::onNext);           // main
    }
}
