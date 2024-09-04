package boki.learnkt.v6_error.b_returnandresume

import boki.learnjava.common.SampleData
import boki.learnkt.common.Book
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import java.util.*

/**
 * onErrorReturn Operator 예제
 *  - 하나 이상의 onErrorReturn()을 사용하여 지정된 타입의 예외가 발생할 경우에만 대체 값을 emit하도록 할 수 있다.
 */
fun main() {
    getBooks()
        .map { book -> book.penName!!.uppercase() }
        .onErrorReturn(NullPointerException::class.java, "no pen name")
        .onErrorReturn(IllegalFormatException::class.java, "Illegal pen name")
        .subscribe(
            { Logger.onNext(it) },
            { Logger.onError(it) }
        )
}

private fun getBooks(): Flux<Book> {
    return Flux.fromIterable(SampleData.books)
}