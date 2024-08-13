package boki.learnkt.v1_newsequence.g_generate

import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import reactor.util.function.Tuples

/**
 * generate 개념 이해 예제
 * - 파라미터
 * 1. Callable: 초기 상태 값 또는 객체를 제공(State Supplier)
 * 2. BiFunc<S, T, S>: SynchronousSink 와 현재 상태(state)를 사용하여 single signal 생성
 * 3. Consumer: Generator 종료 또는 Subscriber의 구독 취소 시, 호출되어 후처리 작업 진행
 */
fun main() {
    Flux
        .generate(
            { Tuples.of(2, 1) },
            { state, sink ->
                sink.next("${state.t1} * ${state.t2} = ${state.t1 * state.t2}")
                if (state.t2 == 9)
                    sink.complete()
                return@generate Tuples.of(state.t1, state.t2 + 1)
            },
            { state -> Logger.info("# 구구단 ${state.t1}단 종료!") }
        )
        .subscribe { Logger.onNext(it) }
}