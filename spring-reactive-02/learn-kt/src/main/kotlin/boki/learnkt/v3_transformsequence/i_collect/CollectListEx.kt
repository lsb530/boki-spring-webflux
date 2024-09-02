package boki.learnkt.v3_transformsequence.i_collect

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * collectList 활용 예제
 * - emit된 세 개의 모스 부호를 List<String> 타입으로 Downstream에 emit한다.
 */
fun main() {
    Flux
        .just("...", "---", "...")
        .mapNotNull { transformMorseCode(it) }
        .collectList() // 반환형: Mono
        .subscribe { list -> Logger.onNext(list.joinToString("")) }
}

private fun transformMorseCode(morseCode: String?): String? {
    return SampleData.morseCodeMap[morseCode]
}