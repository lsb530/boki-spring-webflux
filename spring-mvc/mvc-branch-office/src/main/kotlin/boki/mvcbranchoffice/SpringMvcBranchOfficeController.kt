package boki.mvcbranchoffice

import boki.core.BookResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/books")
@RestController
class SpringMvcBranchOfficeController(
    private val bookMap: Map<Long, BookResponse>
) {
    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): ResponseEntity<BookResponse> {
        Thread.sleep(5000L)

        val book = bookMap[bookId]

        return ResponseEntity.ok(book)
    }
}