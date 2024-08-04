package boki.learnjava.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class FluxEx4 {
    public static void main(String[] args) {
        Flux.concat(
                Flux.just("Venus"),
                Mono.just("Earth"),
                Flux.just("Mars"))
            .collectList() // 주석하고 실행하면 각각의 데이터들이 전달됨
            .subscribe(planetList -> log.info("# Solar System: {}", planetList));
    }
}
