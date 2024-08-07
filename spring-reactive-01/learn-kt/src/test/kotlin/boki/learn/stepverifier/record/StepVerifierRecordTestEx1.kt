package boki.learn.stepverifier.record

import boki.learn.stepverifier.RecordExample
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.Every.everyItem
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

/**
 * emit 되는 모든 데이터들을 캡쳐하여 컬렉션에 기록한 후, 기록된 데이터들을 검증하는 예제
 * - recordWith()를 사용하여 emit 된 데이터를 기록하는 세션을 시작한다.
 * - thenConsumeWhile()을 사용하여 조건에 맞는 데이터만 소비한다. 여기서 조건에 맞는 데이터들이 ArrayList 에 추가(기록)된다.
 * - consumeRecordedWith()를 사용하여 기록된 데이터들을 소비한다. 여기서는 assertThat()을 사용하여 검증한다.
 */
class StepVerifierRecordTestEx1 {

    @Test
    fun getCountryTest() {
        StepVerifier
            .create(RecordExample.getCountry(Flux.just("france", "russia", "greece", "poland")))
            .expectSubscription()
            .recordWith { ArrayList() }
            .thenConsumeWhile { country: String -> country.isNotEmpty() }
            .consumeRecordedWith { countries: Collection<String> ->
                MatcherAssert.assertThat(countries, everyItem(Matchers.hasLength(6)))
                MatcherAssert.assertThat(
                    countries.all { country -> country[0].isUpperCase() },
                    Matchers.`is`(true)
                )
            }
            .expectComplete()
            .verify()
    }
}