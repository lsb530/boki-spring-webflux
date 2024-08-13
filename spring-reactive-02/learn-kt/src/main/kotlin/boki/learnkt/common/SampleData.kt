package boki.learnjava.common

import boki.learnkt.common.Book
import reactor.core.publisher.Mono
import reactor.util.function.Tuple2
import reactor.util.function.Tuples
import java.time.Duration

/**
 * 예제 코드에 사용하는 샘플 데이터
 */
object SampleData {
    val coinNames = listOf("BTC", "ETH", "XRP", "ICX", "EOS", "BCH")
    val btcPrices = listOf(50_000_000, 50_100_000, 50_700_000, 51_500_000, 52_000_000)
    val coins = listOf(
        Tuples.of("BTC", 52_000_000),
        Tuples.of("ETH", 1_720_000),
        Tuples.of("XRP", 533),
        Tuples.of("ICX", 2_080),
        Tuples.of("EOS", 4_020),
        Tuples.of("BCH", 558_000)
    )

    val btcTopPricesPerYear = listOf(
        Tuples.of(2010, 565L),
        Tuples.of(2011, 36_094L),
        Tuples.of(2012, 17_425L),
        Tuples.of(2013, 1_405_209L),
        Tuples.of(2014, 1_237_182L),
        Tuples.of(2015, 557_603L),
        Tuples.of(2016, 1_111_811L),
        Tuples.of(2017, 22_483_583L),
        Tuples.of(2018, 19_521_543L),
        Tuples.of(2019, 15_761_568L),
        Tuples.of(2020, 22_439_002L),
        Tuples.of(2021, 63_364_000L)
    )

    val coronaVaccineNames = CoronaVaccine.toList()

    val coronaVaccines = listOf(
        Tuples.of(CoronaVaccine.Pfizer, 1_000_000),
        Tuples.of(CoronaVaccine.AstraZeneca, 3_000_000),
        Tuples.of(CoronaVaccine.Moderna, 4_000_000),
        Tuples.of(CoronaVaccine.Janssen, 2_000_000),
        Tuples.of(CoronaVaccine.Novavax, 2_500_000)
    )

    val viralVectorVaccines = listOf(
        Tuples.of(CoronaVaccine.AstraZeneca, 3_000_000),
        Tuples.of(CoronaVaccine.Janssen, 2_000_000)
    )

    val mRNAVaccines = listOf(
        Tuples.of(CoronaVaccine.Pfizer, 1_000_000),
        Tuples.of(CoronaVaccine.Moderna, 4_000_000)
    )

    val subunitVaccines = listOf(
        Tuples.of(CoronaVaccine.Novavax, 2_500_000)
    )

    val seoulInfected = listOf(
        Tuples.of(1, 0),
        Tuples.of(2, 0),
        Tuples.of(3, 0),
        Tuples.of(4, 0),
        Tuples.of(5, 0),
        Tuples.of(6, 0),
        Tuples.of(7, 0),
        Tuples.of(8, 0),
        Tuples.of(9, 0),
        Tuples.of(10, 20),
        Tuples.of(11, 23),
        Tuples.of(12, 33),
        Tuples.of(13, 10),
        Tuples.of(14, 15),
        Tuples.of(15, 20),
        Tuples.of(16, 30),
        Tuples.of(17, 10),
        Tuples.of(18, 11),
        Tuples.of(19, 13),
        Tuples.of(20, 8),
        Tuples.of(21, 14),
        Tuples.of(22, 4),
        Tuples.of(23, 7),
        Tuples.of(24, 2)
    )

    val incheonInfected = listOf(
        Tuples.of(1, 0), Tuples.of(2, 0), Tuples.of(3, 0), Tuples.of(4, 0), Tuples.of(5, 0), Tuples.of(6, 0),
        Tuples.of(7, 0), Tuples.of(8, 0), Tuples.of(9, 0), Tuples.of(10, 3), Tuples.of(11, 5), Tuples.of(12, 2),
        Tuples.of(13, 10), Tuples.of(14, 5), Tuples.of(15, 6), Tuples.of(16, 7), Tuples.of(17, 2), Tuples.of(18, 5),
        Tuples.of(19, 2), Tuples.of(20, 0), Tuples.of(21, 2), Tuples.of(22, 0), Tuples.of(23, 2), Tuples.of(24, 1)
    )

    val suwonInfected = listOf(
        Tuples.of(1, 0), Tuples.of(2, 0), Tuples.of(3, 0), Tuples.of(4, 0), Tuples.of(5, 0), Tuples.of(6, 0),
        Tuples.of(7, 0), Tuples.of(8, 0), Tuples.of(9, 0), Tuples.of(10, 2), Tuples.of(11, 1), Tuples.of(12, 0),
        Tuples.of(13, 3), Tuples.of(14, 2), Tuples.of(15, 3), Tuples.of(16, 6), Tuples.of(17, 3), Tuples.of(18, 1),
        Tuples.of(19, 1), Tuples.of(20, 0), Tuples.of(21, 0), Tuples.of(22, 1), Tuples.of(23, 0), Tuples.of(24, 0)
    )

    val morseCodeMap = mutableMapOf<String, String>()
    val nppMap = mutableMapOf<String, Mono<String>>()
    val morseCodes = arrayOf(
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    )

    init {
        for (c in 'a'..'z') {
            morseCodeMap[morseCodes[c - 'a']] = c.toString()
        }

        nppMap["Ontario"] = Mono.just("Ontario Done").delayElement(Duration.ofMillis(1500L))
        nppMap["Vermont"] = Mono.just("Vermont Done").delayElement(Duration.ofMillis(400L))
        nppMap["New Hampshire"] = Mono.just("New Hampshire Done").delayElement(Duration.ofMillis(700L))
        nppMap["New Jersey"] = Mono.just("New Jersey Done").delayElement(Duration.ofMillis(500L))
        nppMap["Ohio"] = Mono.just("Ohio Done").delayElement(Duration.ofMillis(1000L))
        nppMap["Michigan"] = Mono.just("Michigan Done").delayElement(Duration.ofMillis(200L))
        nppMap["Illinois"] = Mono.just("Illinois Done").delayElement(Duration.ofMillis(300L))
        nppMap["Virginia"] = Mono.just("Virginia Done").delayElement(Duration.ofMillis(600L))
        nppMap["North Carolina"] = Mono.just("North Carolina Done").delayElement(Duration.ofMillis(800L))
        nppMap["Georgia"] = Mono.just("Georgia Done").delayElement(Duration.ofMillis(900L))
    }

    fun getCoronaVaccinesMap(): Map<CoronaVaccine, Tuple2<CoronaVaccine, Int>> {
        return coronaVaccines.associateBy({ it.t1 }, { it })
    }

    fun getBtcTopPricesPerYearMap(): Map<Int, Tuple2<Int, Long>> {
        return btcTopPricesPerYear.associateBy({ it.t1 }, { it })
    }

    val books = listOf(
        Book(1, "Advance Java", "Tom", "Tom-boy", 25000, 100),
        Book(2, "Advance Python", "Grace", "Grace-girl", 22000, 150),
        Book(3, "Advance Reactor", "Smith", "David-boy", 35000, 200),
        Book(4, "Getting started Java", "Tom", "Tom-boy", 32000, 230),
        Book(5, "Advance Kotlin", "Kevin", "Kevin-boy", 32000, 250),
        Book(6, "Advance Javascript", "Mike", "Tom-boy", 32000, 320),
        Book(7, "Getting started Kotlin", "Kevin", "Kevin-boy", 32000, 150),
        Book(8, "Getting started Python", "Grace", "Grace-girl", 32000, 200),
        Book(9, "Getting started Reactor", "Smith", null, 32000, 250),
        Book(10, "Getting started Javascript", "Mike", "David-boy", 32000, 330)
    )

    fun findBookById(bookId: Int): Book {
        return books.firstOrNull { it.bookId == bookId }
            ?: throw RuntimeException("Not found book")
    }

    val monthlyBookSales2021 = listOf(
        2_500_000, 3_200_000, 2_300_000, 4_500_000,
        6_500_000, 5_500_000, 3_100_000, 2_000_000,
        2_800_000, 4_100_000, 6_200_000, 4_200_000
    )

    enum class CoronaVaccine {
        Pfizer,
        AstraZeneca,
        Moderna,
        Janssen,
        Novavax;

        companion object {
            fun toList(): List<CoronaVaccine> {
                return listOf(
                    Pfizer,
                    AstraZeneca,
                    Moderna,
                    Janssen,
                    Novavax
                )
            }
        }
    }
}
