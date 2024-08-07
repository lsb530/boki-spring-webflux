package boki.learnjava.coldhot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class ColdSequenceEx {
    public static void main(String[] args) {
        Flux<String> colorFlux = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "GREEN"))
            .map(String::toLowerCase);

        colorFlux.subscribe(color -> log.info("# Subscriber1: {}", color));
        log.info("-------------------------------------");
        colorFlux.subscribe(color -> log.info("# Subscriber2: {}", color));
    }
}
