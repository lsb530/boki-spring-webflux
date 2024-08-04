package boki.learnjava.sink.one;

import boki.learnjava.util.ConsoleColors;
import boki.learnjava.util.Logger;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

/**
 * Sinks.One 예제
 * - 한 건의 데이터만 emit 하는 예제
 */
public class SinkOneEx01 {
    public static void main(String[] args) {
        // emit 된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop 됨.
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", FAIL_FAST);

        mono.subscribe(data -> Logger.onNext(ConsoleColors.BLUE + "Subscriber1", data + ConsoleColors.RESET));
        mono.subscribe(data -> Logger.onNext(ConsoleColors.YELLOW + "Subscriber2", data + ConsoleColors.RESET));
    }
}
