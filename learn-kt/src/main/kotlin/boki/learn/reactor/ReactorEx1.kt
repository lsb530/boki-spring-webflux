package boki.learn.reactor

import reactor.core.publisher.Flux

fun main() {
    // 1. Publisher: 생산자/발행자/게시자/방출자(Flux)
    // 2. 데이터 소스("Hello", "Boki", "Reactor")
    // 3. Emit: 데이터 방출/통지. 시퀀스 생성(Flux.just(...)), Operator 체인형태
    val sequence: Flux<String> = Flux.just("Hello", "Boki", "Reactor")

    // 4. Subscriber: 구독자/소비자는 Sequence를 구독 or 해지
    sequence.map { it.lowercase() }
        .subscribe { println(it) }
}