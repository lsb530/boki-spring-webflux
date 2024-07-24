package boki.reactivebranchoffice

import boki.core.BookResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RequestMapping("/v1/books")
//@CrossOrigin(origins = "*")
@RestController
class SpringReactiveBranchOfficeController(
    private val bookMap: Map<Long, BookResponse>
) {
    @Throws(InterruptedException::class)
    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): Mono<BookResponse> {
        Thread.sleep(5000L)

        val book = bookMap[bookId]

        return book?.let { Mono.just(book) } ?: Mono.empty()
    }
}
