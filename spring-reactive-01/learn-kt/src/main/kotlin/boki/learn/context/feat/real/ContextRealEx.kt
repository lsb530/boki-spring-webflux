package boki.learn.context.feat.real

import boki.learn.util.Logger
import reactor.core.publisher.Mono
import reactor.util.context.Context

const val HEADER_NAME_AUTH_TOKEN = "authToken"

/**
 * Context 활용 예제
 * - 직교성을 갖는 정보를 표현할 때 주로 사용된다.
 * - 직교성: 애플리케이션의 실행에 영향을 주지 않는 정보
 */
fun main() {
    val mono = postBook(
        Mono.just(
            Book(
                isbn = "abcd-1111-3533-2809",
                bookName = "Reactor's Bible",
                author = "code-boki"
            )
        )
    ).contextWrite { Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGciOiJIUzUxMiJ9.eyJzdWI") }

    mono.subscribe { Logger.onNext(it) }
}

private fun postBook(book: Mono<Book>): Mono<String> {
    return Mono.zip(book, Mono.deferContextual { Mono.just(it.get<String>(HEADER_NAME_AUTH_TOKEN)) })
        .flatMap { Mono.just(it) } // 외부 API 서버로 HTTP POST request를 전송한다고 가정
        .flatMap { tuple ->
            val response = "POST the book(${tuple.t1.bookName}/${tuple.t1.author}) with token: ${tuple.t2}"
            return@flatMap Mono.just(response)  // HTTP response를 수신했다고 가정
        }
}