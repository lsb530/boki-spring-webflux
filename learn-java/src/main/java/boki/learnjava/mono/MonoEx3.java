package boki.learnjava.mono;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

/**
 * Mono를 활용한 예제
 * - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다
 */
@Slf4j
public class MonoEx3 {
    public static void main(String[] args) {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("worldtimeapi.org")
            .port(80)
            .path("/api/timezone/Asia/Seoul")
            .build()
            .encode()
            .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Mono.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<>(headers), String.class)
            )
            .map(response -> {
                DocumentContext jsonContext = JsonPath.parse(response.getBody());
                return jsonContext.<String>read("$.datetime");
            })
            .doOnNext(data -> log.info("# emitted data: {}", data))
            .doOnError(error -> log.error("# error occured: {}", error))
            .doOnTerminate(() -> log.info("# emitted onComplete signal"))
            .subscribe();
            // .subscribe(
            //     data -> log.info("# emitted data: {}", data),
            //     error -> log.error("# error occured: {}", error),
            //     () -> log.info("# emitted onComplete signal")
            // );
    }
}
