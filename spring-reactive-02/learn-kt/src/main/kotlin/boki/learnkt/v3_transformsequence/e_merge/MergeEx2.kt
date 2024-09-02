package boki.learnkt.v3_transformsequence.e_merge

import boki.learnjava.common.SampleData
import boki.learnkt.util.Logger
import boki.learnkt.util.TimeUtils
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * merge 활용 예제
 *  - merge를 이용하여 melt down 되고 있는 미 동부 원자력 발전소가 복구되는 메시지를 출력하는 예제
 */
fun main() {
    val usaStates = arrayOf(
        "Ohio", "Michigan", "New Jersey", "Illinois", "New Hampshire",
        "Virginia", "Vermont", "North Carolina", "Ontario", "Georgia"
    )

    Flux
        .merge(getMeltDownRecoveryMessage(usaStates))
        .subscribe { Logger.onNext(it) }

    TimeUtils.sleep(2000L)
}

private fun getMeltDownRecoveryMessage(usaStates: Array<String>): List<Mono<String>?> {
    val messages: MutableList<Mono<String>?> = ArrayList()

    for (state in usaStates) {
        messages.add(SampleData.nppMap[state])
    }

    return messages
}