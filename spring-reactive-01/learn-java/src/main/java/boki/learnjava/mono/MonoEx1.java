package boki.learnjava.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoEx1 {
    public static void main(String[] args) {
        Mono.just("Hello Reactor!")
            .subscribe(data -> log.info("# emitted data: {}", data));
    }
}
