package boki.learnkt.v3_transformsequence.a_map

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * map 기본 개념 예제
 *  - Upstream에서 emit된 데이터를 반환한 후, DownStream으로 emit
 */
fun main() {
   Flux
       .just("Green-Circle", "Yellow-Circle", "Blue-Circle", "Red-Circle")
       .map { it.replace("Circle", "Rectangle") }
       .subscribe { Logger.onNext(it) }
}