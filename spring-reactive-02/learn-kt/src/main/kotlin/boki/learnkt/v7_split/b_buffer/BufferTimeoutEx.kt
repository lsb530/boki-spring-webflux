package boki.learnkt.v7_split.b_buffer

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * bufferTimeout 기본 개념 예제
 *  - Upstream에서 emit된 데이터가 버퍼에 채워질 때, maxTime에 도달하면 버퍼를 비운다.
 *  - maxTime에 도달하기 전에 maxSize 만큼의 데이터가 버퍼에 채워지면 maxTime까지 기다리지 않고, 버퍼를 비운다.
 */
fun main() {
    Flux
        .range(1, 20)
        .map { num ->
            when {
                num < 10 -> TimeUtils.sleep(100L)
                else -> TimeUtils.sleep(300L)
            }
            return@map num
        }
        .bufferTimeout(3, Duration.ofMillis(400L))
        .subscribe { Logger.onNext(it) }
}