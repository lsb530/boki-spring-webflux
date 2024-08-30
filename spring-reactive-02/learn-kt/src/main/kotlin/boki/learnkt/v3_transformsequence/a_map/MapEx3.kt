package boki.learnkt.v3_transformsequence.a_map

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * map 활용 예제
 * - Morse Code를 알파벳으로 변환하는 예제
 */
fun main() {
    Flux
        .just("...", "---", "...")
        .mapNotNull { transformMorseCode(it) }
        .subscribe { Logger.onNext(it) }
}

private fun transformMorseCode(morseCode: String): String? {
    return SampleData.morseCodeMap[morseCode]
}