package boki.mvcheadoffice

import boki.core.BookRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.time.LocalTime

@SpringBootApplication
class MvcHeadOfficeApplication(
    private val restTemplate: RestTemplate,
) {
    private val log = LoggerFactory.getLogger(MvcHeadOfficeApplication::class.java)

    private val baseUri: URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("localhost")
        .port(8080)
        .path("/v1/books")
        .build()
        .encode()
        .toUri()

    @Bean
    fun run(restTemplate: RestTemplate): CommandLineRunner {
        return CommandLineRunner {
            log.debug("# 요청 시작 시간: {}", LocalTime.now())

            for (i in 1..5) {
                val book = getBook(i.toLong())
                log.debug("response time: {}, book name: {}, book price: {}", LocalTime.now(), book?.name, book?.price)
            }
        }
    }

    private fun getBook(bookId: Long): BookRequest? {
        val getBooksUri = UriComponentsBuilder.fromUri(baseUri)
            .path("/{book-id}")
            .build()
            .expand(bookId)
            .encode()
            .toUri() // http://localhost:8080/v1/books/{book-id}

        val response: ResponseEntity<BookRequest> = restTemplate.getForEntity(getBooksUri, BookRequest::class.java)
        return response.body
    }
}

fun main(args: Array<String>) {
    runApplication<MvcHeadOfficeApplication>(*args)
}
