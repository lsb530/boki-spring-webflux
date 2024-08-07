package boki.learnjava.stepverifier.general;

import lombok.val;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * StepVerifier 기본 사용 예제
 */
class StepVerifierGeneralTestEx1 {

    @Test
    void sayHelloReactorTest() {
        val actual = "Hello Reactor";
        val expected = "Hello Reactor";

        StepVerifier
            .create(Mono.just(actual)) // 테스트 대상 Sequence 생성
            .expectNext(expected) // onNext Signal 에 대한 emit 된 데이터 검증
            .expectComplete() // onComplete Signal 검증
            .verify(); // 검증 실행
    }
}
