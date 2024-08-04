package boki.learnjava.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
public class FluxEx3 {
    // Mono + Mono = Flux
    public static void main(String[] args) {
        // Flux<Object> flux = Mono.justOrEmpty(null)
        Flux<Object> flux = Mono.justOrEmpty(Optional.empty())
            .concatWith(Mono.justOrEmpty("Jobs"));

        flux.subscribe(data -> log.info("# result: {}", data));
    }
}
