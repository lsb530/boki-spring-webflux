package boki.learnjava.coldhot;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
public class HotSequenceEx {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
            .delayElements(Duration.ofSeconds(1)).share();// share() 원본 Flux를 여러 Subscriber가 공유

        // concertFlux.subscribe(singer -> log.info("# Subscriber1 is watching {}'s song", singer));
        concertFlux.subscribe(singer -> System.out.printf("# Subscriber1 is watching %s's song\n", singer));

        Thread.sleep(2500);

        // concertFlux.subscribe(singer -> log.info("# Subscriber2 is watching {}'s song", singer));
        // C부터 구독했기 때문에 C, D, E의 데이터만 받을 수 있음
        concertFlux.subscribe(singer -> System.out.printf("# Subscriber2 is watching %s's song\n", singer));

        Thread.sleep(3000); // 메인스레드가 모두 종료되지 않게 하기 위함

    }
}
