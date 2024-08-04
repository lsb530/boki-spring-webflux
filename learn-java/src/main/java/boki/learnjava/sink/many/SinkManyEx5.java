package boki.learnjava.sink.many;

import boki.learnjava.util.ConsoleColors;
import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import static reactor.core.publisher.Sinks.*;
import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

/**
 * Sinks.Many 예제
 * - replay().all()을 사용하여 구독 시점과 상관없이 모든 데이터를 replay한다
 */
public class SinkManyEx5 {
    public static void main(String[] args) {
        // 구독 시점과 상관없이 emit된 모든 데이터를 replay한다
        Sinks.Many<Object> replaySink = many().replay().all();
        Flux<Object> fluxView = replaySink.asFlux();

        replaySink.emitNext(1, FAIL_FAST);
        replaySink.emitNext(2, FAIL_FAST);
        replaySink.emitNext(3, FAIL_FAST);

        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.BLUE + "Subscriber1", data + ConsoleColors.RESET));
        fluxView.subscribe(data -> Logger.onNext(ConsoleColors.YELLOW + "Subscriber2", data + ConsoleColors.RESET));

        TimeUtils.sleep(1000L);
    }
}
