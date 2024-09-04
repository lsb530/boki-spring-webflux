package boki.learnkt.v6_error.e_retry

import boki.learnjava.common.SampleData
import boki.learnkt.common.Book
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Collectors

/**
 * retry 활용 예제
 *   - 지정된 시간 안에 특정 도서 정보를 가지고 오지 못할 경우, TimeoutException이 발생한다.
 *   - retry()를 이용해 재시도한다.
 */
fun main() {
    getAllBooksFromRemoteDB()
        .collect(Collectors.toSet())
        .subscribe { bookSet ->
            bookSet.forEach {
                Logger.onNext("book name: ${it.bookName}, price: ${it.price}")
            }
        }

    Thread.sleep(12000L)
}

private fun getAllBooksFromRemoteDB(): Flux<Book> {
    val count = intArrayOf(0)
    return Flux
        .fromIterable<Book>(SampleData.books)
        .delayElements(Duration.ofMillis(500))
        .map { book ->
            try {
                count[0]++
                if (count[0] == 3) {
                    Thread.sleep(2000)
                }
            } catch (_: InterruptedException) {
                //
            }
            return@map book
        }
        .timeout(Duration.ofSeconds(2))
        .doOnNext { Logger.doOnNext(it.bookName, it.price) }
        .retry(1)
}