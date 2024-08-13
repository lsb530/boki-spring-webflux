package boki.learnkt.v1_newsequence.g_generate

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * generate 개념 이해 예제
 * - 파라미터
 * 1. Callable: 초기 상태 값 또는 객체를 제공(State Supplier)
 * 2. BiFunc<S, T, S>: SynchronousSink 와 현재 상태(state)를 사용하여 single signal 생성
 */
fun main() {
    Flux
        .generate(
            { 0 },
            { state, sink ->
                sink.next(state)
                if (state == 10)
                    sink.complete()
                state + 1
            }
        )
        .subscribe { Logger.onNext(it) }
}