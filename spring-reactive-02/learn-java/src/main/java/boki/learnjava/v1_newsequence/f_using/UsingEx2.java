package boki.learnjava.v1_newsequence.f_using;

import boki.learnjava.utils.Logger;
import boki.learnjava.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * using()을 사용하기 적절한 예제
 * - 파라미터
 * 1. Callable: Resource를 input으로 제공
 * 2. Function: input으로 전달받은 Resource를 새로 생성한 Publisher로 emit(Flux)
 * 3. Consumer: 사용이 끝난 Resource를 해제
 */
public class UsingEx2 {
    public static void main(String[] args) {
        Path sampleFile = Paths.get("spring-reactive-02/learn-java/src/main/resources/using_example.txt");

        Flux
            .using(
                () -> Files.lines(sampleFile),
                Flux::fromStream,
                Stream::close
            )
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe(Logger::onNext);

        TimeUtils.sleep(100);
    }
}
