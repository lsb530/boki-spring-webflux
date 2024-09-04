package boki.learnkt.v6_error.b_returnandresume

import boki.learnjava.common.SampleData
import boki.learnjava.common.SampleData.books
import boki.learnkt.common.Book
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * onErrorResume 활용 예제
 *  - 예외가 발생했을 때, onError signal을 Downstream으로 전송하지 않고, 대체 Publisher로 데이터를 emit하고자 할 경우
 */
fun main() {
    val keyword = "DDD"
    getBooksFromCache(keyword)
        .onErrorResume { _ -> getBooksFromDatabase(keyword) }
        .subscribe(
            { data -> Logger.onNext(data.bookName) },
            { Logger.onError(it) }
        )
}

private fun getBooksFromCache(keyword: String): Flux<Book> {
    return Flux
        .fromIterable(books)
        .filter { book -> book.bookName!!.contains(keyword) }
        .switchIfEmpty(Flux.error(NoSuchBookException("No such Book")))
}

private fun getBooksFromDatabase(keyword: String): Flux<Book> {
    val books = SampleData.books.toMutableList()
    books.add(
        Book(
            11, "DDD: Domain Driven Design",
            "Joy", "ddd-man", 35000, 200
        )
    )
    return Flux
        .fromIterable(books)
        .filter { book -> book.bookName!!.contains(keyword) }
        .switchIfEmpty(Flux.error(NoSuchBookException("No such Book")))
}

private class NoSuchBookException(message: String) : RuntimeException(message)
