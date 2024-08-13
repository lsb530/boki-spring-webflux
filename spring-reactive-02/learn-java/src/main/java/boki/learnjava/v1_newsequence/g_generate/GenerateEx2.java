package boki.learnjava.v1_newsequence.g_generate;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * generate 개념 이해 예제
 * - 파라미터
 * 1. Callable: 초기 상태 값 또는 객체를 제공(State Supplier)
 * 2. BiFunc<S, T, S>: SynchronousSink 와 현재 상태(state)를 사용하여 single signal 생성
 * 3. Consumer: Generator 종료 또는 Subscriber의 구독 취소 시, 호출되어 후처리 작업 진행
 */
public class GenerateEx2 {
    public static void main(String[] args) {
        Flux
            .generate(() -> Tuples.of(2, 1), (state, sink) -> {
                    sink.next(state.getT1() + " * " + state.getT2() + " = " + state.getT1() * state.getT2());
                    if (state.getT2() == 9)
                        sink.complete();
                    return Tuples.of(state.getT1(), state.getT2() + 1);
                },
                state -> Logger.info("# 구구단 {}단 종료!", state.getT1())
            )
            .subscribe(Logger::onNext);
    }
}
