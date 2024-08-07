package boki.learnjava.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorEx1 {
    public static void main(String[] args) {
        Flux<String> sequence = Flux.just("Hello", "Boki", "Reactor");

        sequence.map(data -> data.toLowerCase())
            .subscribe(data -> log.info(data));
    }
}
