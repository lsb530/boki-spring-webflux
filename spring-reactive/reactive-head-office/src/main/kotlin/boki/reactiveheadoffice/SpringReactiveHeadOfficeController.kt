package boki.reactiveheadoffice

import boki.core.BookResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

@RequestMapping("/v1/books")
@RestController
class SpringReactiveHeadOfficeController {
    var baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(5050)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @GetMapping("/{book-id}")
    @Throws(InterruptedException::class)
    fun getBook(@PathVariable("book-id") bookId: Long): Mono<BookResponse> {
        val getBookUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:5050/v1/books/{book-id}

        return WebClient.create()
            .get()
            .uri(getBookUri)
            .retrieve()
            .bodyToMono(BookResponse::class.java)
    }
}
