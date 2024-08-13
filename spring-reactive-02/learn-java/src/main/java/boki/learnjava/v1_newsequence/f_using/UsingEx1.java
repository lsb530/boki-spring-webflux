package boki.learnjava.v1_newsequence.f_using;

import boki.learnjava.utils.Logger;
import reactor.core.publisher.Mono;

/**
 * using() 예제
 * - 파라미터
 * 1. Callable: Resource를 input으로 제공
 * 2. Function: input으로 전달받은 Resource를 새로 생성한 Publisher로 emit(Flux)
 * 3. Consumer: 사용이 끝난 Resource를 해제
 */
public class UsingEx1 {
    public static void main(String[] args) {
        Mono
            .using(
                () -> "Resource",
                Mono::just,
                resource -> Logger.info("cleanup: {}", resource)
            )
            .subscribe(Logger::onNext);
    }
}
