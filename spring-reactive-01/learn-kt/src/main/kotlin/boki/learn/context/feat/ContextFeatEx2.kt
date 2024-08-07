package boki.learn.context.feat

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Context의 특징
 * - Context는 체인의 맨 아래에서부터 위로 전파된다.
 * - 따라서 Operator 체인에서 Context read 읽는 동작이 Context write 동작 밑에 있을 경우에는 write된 값을 read할 수 없다.
 */
fun main() {
    val key1 = "id"
    val key2 = "name"

    Mono.deferContextual { ctx ->
        val id: String = ctx[key1]
        Mono.just("ID: $id")
    }
        .publishOn(Schedulers.parallel())
        // 컨텍스트는 아래에서부터 올라오기 때문에 의미없는 코드
        .contextWrite { it.put(key2, "boki") }
        .transformDeferredContextual { m, cv ->
            val name = cv.getOrDefault(key2, "Sam")
            m.map { data -> "$data, $name" }  // 이 시점의 key2는 값이 없음
        }
        .contextWrite { it.put(key1, "개발꾼") }
        .subscribe { Logger.onNext(it) }

    Thread.sleep(100L)
}