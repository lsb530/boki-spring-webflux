package boki.learnkt.v6_error.d_map

import boki.learnkt.common.TimezoneNotFoundException
import boki.learnkt.util.Logger
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.netty.resolver.DefaultAddressResolverGroup
import org.springframework.http.*
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.util.concurrent.CountDownLatch

/**
 * onErrorMap 활용 예제
 * - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 * - 404 Not Found가 발생할 경우, HttpClientErrorException을 조금 더 구체적인 TimezoneNotFoundException으로 변경한다.
 */
fun main() {
    val latch = CountDownLatch(1) // 비동기 작업이 끝날 때까지 기다릴 latch

    // callWorldTimeApiByRestTemplate()
    callWorldTimeApiByWebClient()
        .doOnSubscribe { Logger.doOnSubscribe() }
        .doOnRequest { Logger.doOnRequest() }
        .doOnNext { Logger.doOnNext(it) }
        .onErrorMap { ex ->
            when (ex) {
                is HttpClientErrorException -> {
                    if (ex.statusCode == HttpStatus.NOT_FOUND) {
                        TimezoneNotFoundException(ex.responseBodyAsString)
                    } else {
                        HttpClientErrorException(ex.statusCode)
                    }
                }
                else -> ex
            }
        }
        .map { response ->
            val mapper = jacksonObjectMapper()
            val jsonNode: JsonNode = mapper.readTree(response)
            jsonNode["datetime"].asText()
        }
        .doOnNext { Logger.onNext(it) }
        .doOnError { Logger.onError(it) }
        .doOnTerminate { Logger.doOnTerminate() }
        .doFinally { latch.countDown() }
        .subscribe()

    latch.await()
}

private val WORLD_TIME_URI = UriComponentsBuilder.newInstance().scheme("http")
    .host("worldtimeapi.org")
    .port(80) // .path("/api/timezone/Asia/Mars") // 잘못된 URI 입력
    .path("/api/timezone/Asia/Seoul") // 알맞은 URI 입력
    .build()
    .encode()
    .toUri()

private fun callWorldTimeApiByRestTemplate(): Mono<String?> {
    val restTemplate = RestTemplate()
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)

    val entity = HttpEntity<String>(headers)

    return Mono.fromSupplier {
        restTemplate.exchange(WORLD_TIME_URI, HttpMethod.GET, entity, String::class.java)
    }.mapNotNull { responseEntity -> responseEntity.body }
}

private fun callWorldTimeApiByWebClient(): Mono<String> {
    val httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)

    val webClient = WebClient.builder()
        .clientConnector(ReactorClientHttpConnector(httpClient))
        .baseUrl(WORLD_TIME_URI.toString())
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build()

    return webClient.get()
        .retrieve()
        .bodyToMono(String::class.java)
}

