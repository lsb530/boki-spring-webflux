package boki.learn.mono

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

/**
 * Mono를 활용한 예제
 * - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다
 */
fun main() {
    val log = LoggerFactory.getLogger("MonoEx3")

    val objectMapper = ObjectMapper()

    val worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Seoul")
        .build()
        .encode()
        .toUri()

    val restTemplate = RestTemplate()
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)

    Mono.just(
        restTemplate.exchange(
            worldTimeUri, HttpMethod.GET, HttpEntity<Any>(headers),
            String::class.java
        )
    )
        .map { response ->
            val jsonNode = objectMapper.readTree(response.body)
            val dateTime = jsonNode.get("datetime").asText()
            return@map dateTime
        }
        .doOnNext { data -> log.info("# emitted data: $data") }
        .doOnError { error -> log.error("# error occurred: $error") }
        .doOnTerminate { log.info("# emitted onComplete signal") }
        .subscribe()
}