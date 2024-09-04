package boki.learnjava.v6_error.a_error;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

/**
 * error Operator 활용 예제 코드
 * flatMap처럼 Inner Sequence가 존재하는 경우, 조건에 따라 onError Signal을 전송할 수 있다.
 */
public class ErrorEx2 {
    public static void main(String[] args) {
        Flux
            .just('a', 'b', 'c', '3', 'd')
            .flatMap(ErrorEx2::convert)
            .subscribe(
                Logger::onNext,
                Logger::onError
            );
    }

    private static Mono<String> convert(char ch) {
        if (!Character.isAlphabetic(ch)) {
            return Mono.error(new DataFormatException("Not Alphabetic"));
        }
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}
