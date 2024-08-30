package boki.learnjava.v3_transformsequence.a_map;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * map 기본 개념 예제
 *  - Upstream에서 emit된 데이터를 반환한 후, DownStream으로 emit
 */
public class MapEx1 {
    public static void main(String[] args) {
        Flux
            .just("Green-Circle", "Yellow-Circle", "Blue-Circle", "Red-Circle")
            .map(circle -> circle.replace("Circle", "Rectangle"))
            .subscribe(Logger::onNext);
    }
}
