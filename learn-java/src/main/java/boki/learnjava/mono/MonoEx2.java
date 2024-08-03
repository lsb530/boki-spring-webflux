package boki.learnjava.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoEx2 {
    public static void main(String[] args) {
        Mono.empty()
            .subscribe(
                data -> log.info("# emitted data: {}", data),
                error -> { },
                () -> log.info("# emitted onComplete signal")
            );

        // 같은 코드
        Mono.empty()
            .doOnNext(data -> log.info("# emitted data: {}", data))
            .doOnError(error -> log.error("Error occurred: {}", error))
            .doOnTerminate(() -> log.info("# emitted onComplete signal"))
            .subscribe();
    }
}
