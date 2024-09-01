package boki.learnjava.v3_transformsequence.b_flatmap;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * flatMap에서 flat의 의미를 이해하기 위한 예제
 * - 들어오는 데이터들이 Publisher(Mono 또는 Flux)로 감싸져 있다면, 감싸져 있는 Publisher들을 벗겨내고 하나의 Publisher로 평면화한다
 */
public class FlatMapEx5 {
    public static void main(String[] args) {
        Flux
            .just("Hello", "World")
            .map(Mono::just)
            .flatMap(word -> word)
            .subscribe(Logger::onNext);
    }
}
