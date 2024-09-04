package boki.learnjava.v6_error.d_map;

import boki.learnjava.common.TimezoneNotFoundException;
import boki.learnjava.utils.Logger;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

/**
 * onErrorMap 활용 예제
 * - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 * - 404 Not Found가 발생할 경우, HttpClientErrorException을 조금 더 구체적인 TimezoneNotFoundException으로 변경한다.
 */
public class OnErrorMapEx2 {
    private final static URI WORLD_TIME_URI = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        // .path("/api/timezone/Asia/Mars") // 잘못된 URI 입력
        .path("/api/timezone/Asia/Seoul") // 알맞은 URI 입력
        .build()
        .encode()
        .toUri();

    private static Mono<String> callWorldTimeApiByRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return Mono.fromSupplier(() ->
            restTemplate.exchange(WORLD_TIME_URI, HttpMethod.GET, new HttpEntity<>(headers), String.class)
        ).map(ResponseEntity::getBody);
    }

    private static Mono<String> callWorldTimeApiByWebClient() {
        HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);

        WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl(WORLD_TIME_URI.toString())
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build();

        return webClient.get()
            .retrieve()
            .bodyToMono(String.class);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1); // 비동기 작업이 끝날 때까지 기다릴 latch

        // callWorldTimeApiByRestTemplate()
        callWorldTimeApiByWebClient()
            .doOnSubscribe(Logger::doOnSubscribe)
            .doOnRequest(Logger::doOnRequest)
            .doOnNext(Logger::doOnNext)
            .onErrorMap(HttpClientErrorException.class, (HttpClientErrorException ex) -> {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    return new TimezoneNotFoundException(ex.getResponseBodyAsString());
                }
                return new HttpClientErrorException(ex.getStatusCode());
            })
            .map(response -> {
                DocumentContext jsonContext = JsonPath.parse(response);
                return jsonContext.<String>read("$.datetime");
            })
            .doOnNext(Logger::onNext)
            .doOnError(Logger::onError)
            .doOnTerminate(Logger::onComplete)
            .doFinally(signalType -> latch.countDown())
            .subscribe();

        latch.await(); // 비동기 작업이 완료될 때까지 메인 스레드를 대기시킴
    }
}
