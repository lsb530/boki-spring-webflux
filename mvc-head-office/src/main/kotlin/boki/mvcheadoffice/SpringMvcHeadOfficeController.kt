package boki.mvcheadoffice

import boki.core.BookRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RequestMapping("/v1/books")
@RestController
class SpringMvcHeadOfficeController(
    private val restTemplate: RestTemplate,
) {
    var baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(7070)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @GetMapping("/{book-id}")
    fun getBook(@PathVariable("book-id") bookId: Long): ResponseEntity<BookRequest> {
        val getBookUri: URI = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:7070/v1/books/{book-id}

        val response: ResponseEntity<BookRequest> = restTemplate.getForEntity(getBookUri, BookRequest::class.java)
        val book: BookRequest? = response.body

        return ResponseEntity.ok(book)
    }
}