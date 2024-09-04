package boki.learnkt.v6_error.b_returnandresume

import boki.learnjava.common.SampleData
import boki.learnkt.common.Book
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * onErrorReturn 예제
 *  - 예외가 발생했을 때, error signal을 Downstream으로 전송하지 않고, default value로 대체해서 emit하고자 할 경우
 *  - try ~ catch 문의 경우, catch해서 default value를 return 하는 것과 같다.
 */
fun main() {
    getBooks()
        .map { it.penName?.uppercase() }
        .onErrorReturn("No pen name")
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) }
        )
}

private fun getBooks(): Flux<Book> {
    return Flux.fromIterable(SampleData.books)
}