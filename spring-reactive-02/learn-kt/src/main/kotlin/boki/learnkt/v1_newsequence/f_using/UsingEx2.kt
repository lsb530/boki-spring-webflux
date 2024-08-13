package boki.learnkt.v1_newsequence.f_using

import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.nio.file.Files
import java.nio.file.Paths

/**
 * using()을 사용하기 적절한 예제
 * - 파라미터
 * 1. Callable: Resource를 input으로 제공
 * 2. Function: input으로 전달받은 Resource를 새로 생성한 Publisher로 emit(Flux)
 * 3. Consumer: 사용이 끝난 Resource를 해제
 */
fun main() {
    val sampleFile = Paths.get("spring-reactive-02/learn-kt/src/main/resources/using_example.txt")

    Flux
        .using(
            { Files.lines(sampleFile) },
            { Flux.fromStream { it } },
            { stream -> stream.close() }
        )
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(100)
}