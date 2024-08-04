package boki.learnjava.sink.many;

import boki.learnjava.util.ConsoleColors;
import boki.learnjava.util.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.*;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

/**
 * Sinks.Many 예제
 * - replay()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
public class SinkManyEx3 {
    public static void main(String[] args) {
        // 구독 이후, emit된 데이터 중에서 최신 데이터 2개만 replay한다
        Sinks.Many<Object> replaySink = many().replay().limit(2);
        Flux<Object> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.BLUE + "Subscriber1", data + ConsoleColors.RESET));
        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.YELLOW + "Subscriber2", data + ConsoleColors.RESET));
    }
}
