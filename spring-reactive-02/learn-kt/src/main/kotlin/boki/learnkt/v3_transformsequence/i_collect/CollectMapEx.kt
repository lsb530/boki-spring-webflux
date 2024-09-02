package boki.learnkt.v3_transformsequence.i_collect

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import reactor.core.publisher.Flux

/**
 * collectMap 활용 예제
 *  - 모스 부호를 key로, 모스 부호에 해당되는 알파벳을 value로 하는 Map을 반환하는 예제
 */
fun main() {
    Flux
        .range(0, 26) // 반환형: Mono, 해시맵을 사용하기에 순서를 보장하지는 않음
        // .collectMap { key -> SampleData.morseCodeMap[key], transformToLetter(key) } // 이렇게 사용하면 안됨
        .collectMap(
            // 2개의 람다 필요
            { key -> SampleData.morseCodes[key] }, { value -> transformToLetter(value) }
        )
        .subscribe { Logger.onNext(it) }

}

private fun transformToLetter(value: Int): String {
    return ('a'.code + value).toChar().toString()
}