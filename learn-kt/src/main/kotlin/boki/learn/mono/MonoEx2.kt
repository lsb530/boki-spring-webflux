package boki.learn.mono

import reactor.core.publisher.Mono

fun main() {
    Mono.empty<Any>()
        .subscribe(
            { data: Any -> println("# emitted data: $data") },
            { error: Throwable -> {} },
            { println("# emitted onComplete signal") }
        )

    // 같은 코드
    Mono.empty<Any>()
        .doOnNext { data -> println("# emitted data: $data") }
        .doOnError { error -> println("Error occurred: $error") }
        .doOnTerminate { println("# emitted onComplete signal") }
        .subscribe()
}