package boki.learnjava.sink.many;

import boki.learnjava.util.ConsoleColors;
import boki.learnjava.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.*;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

/**
 * Sinks.Many 예제
 * - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
 */
public class SinkManyEx1 {
    public static void main(String[] args) {
        // 단 하나의 Subscriber에게만 데이터를 emit할 수 있다
        Sinks.Many<Object> unicastSink = many().unicast().onBackpressureBuffer();
        Flux<Object> fluxView = unicastSink.asFlux();

        unicastSink.emitNext(1, FAIL_FAST);
        unicastSink.emitNext(2, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.BLUE + "Subscriber1", data + ConsoleColors.RESET));

        unicastSink.emitNext(3, FAIL_FAST);

        // 두 개의 구독은 허용하지 않음
        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.YELLOW + "Subscriber2", data + ConsoleColors.RESET));
    }
}
