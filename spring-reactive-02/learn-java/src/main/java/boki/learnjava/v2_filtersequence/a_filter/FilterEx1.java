package boki.learnjava.v2_filtersequence.a_filter;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * Filter 기본 개념 예제
 */
public class FilterEx1 {
    public static void main(String[] args) {
        Flux
            .range(1, 20)
            .filter(num -> num % 2 == 0)
            .subscribe(Logger::onNext);
    }
}
