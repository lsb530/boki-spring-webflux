package boki.learnjava.sink.many;

import boki.learnjava.util.ConsoleColors;
import boki.learnjava.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.*;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

/**
 * Sinks.Many 예제
 * - multicast()를 사용해서 하나 이상의 Subscriber에게 데이터를 emit하는 예제
 */
public class SinkManyEx2 {
    public static void main(String[] args) {
        // 하나 이상의 Subscriber에게만 데이터를 emit할 수 있다
        Sinks.Many<Object> unicastSink = many().multicast().onBackpressureBuffer();
        Flux<Object> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.BLUE + "Subscriber1", data + ConsoleColors.RESET));
        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.YELLOW + "Subscriber2", data + ConsoleColors.RESET));

        // Sub2는 3만 받는다. Hot Sequence/Warm up 방식으로 동작하기 때문
        unicastSink.emitNext(3, FAIL_FAST);
    }
}
