package boki.learn.stepverifier

import reactor.core.publisher.Flux
import java.util.*

class RecordExample {
    companion object {
        fun getCountry(source: Flux<String>): Flux<String> {
            return source.map { country: String ->
                country.substring(0, 1)
                    .uppercase(Locale.getDefault()) + country.substring(1)
            }
        }
    }
}