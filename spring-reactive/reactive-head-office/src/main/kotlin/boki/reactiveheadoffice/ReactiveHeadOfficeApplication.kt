package boki.reactiveheadoffice

import boki.core.BookRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI
import java.time.LocalTime


@SpringBootApplication
class ReactiveHeadOfficeApplication {
    private val log = LoggerFactory.getLogger(ReactiveHeadOfficeApplication::class.java)

    private val baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(6060)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @Bean
    fun run(): CommandLineRunner {
        return CommandLineRunner {
            log.debug("# 요청 시작 시간: {}", LocalTime.now())

            for (i in 1..5) {
                getBook(i.toLong())
                    .subscribe { book: BookRequest ->
                        log.debug(
                            "response time: {}, book name: {}, book price: {}",
                            LocalTime.now(),
                            book.name,
                            book.price
                        )
                    }
            }
        }
    }

    private fun getBook(bookId: Long): Mono<BookRequest> {
        val getBooksUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:6060/v1/books/{book-id}

        return WebClient.create()
            .get()
            .uri(getBooksUri)
            .retrieve()
            .bodyToMono(BookRequest::class.java)
    }
}

fun main(args: Array<String>) {
    System.setProperty("reactor.netty.ioWorkerCount", "1")
    runApplication<ReactiveHeadOfficeApplication>(*args)
}
