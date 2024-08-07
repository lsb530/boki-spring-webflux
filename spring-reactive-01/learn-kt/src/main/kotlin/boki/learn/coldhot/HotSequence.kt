package boki.learn.coldhot

import boki.learn.util.ConsoleColors
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

fun main() {
    val concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
        .delayElements(Duration.ofSeconds(1)).share() // share() 원본 Flux를 여러 Subscriber가 공유

    // concertFlux.subscribe { println("# Subscriber1 is watching $it's song") }
    concertFlux.subscribe { println("${ConsoleColors.RED} # Subscriber1 is watching $it's song ${ConsoleColors.RESET}") }
    Thread.sleep(2500)

    // concertFlux.subscribe { println("# Subscriber2 is watching $it's song") }
    concertFlux.subscribe { println("${ConsoleColors.BLUE} # Subscriber2 is watching $it's song ${ConsoleColors.RESET}") }
    Thread.sleep(3000)
}
