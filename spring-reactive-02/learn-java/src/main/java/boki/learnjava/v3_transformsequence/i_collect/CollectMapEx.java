package boki.learnjava.v3_transformsequence.i_collect;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * collectMap 활용 예제
 *  - 모스 부호를 key로, 모스 부호에 해당되는 알파벳을 value로 하는 Map을 반환하는 예제
 */
public class CollectMapEx {
    public static void main(String[] args) {
        Flux
            .range(0, 26)
            // 반환형: Mono, 해시맵을 사용하기에 순서를 보장하지는 않음
            .collectMap(key -> SampleData.morseCodes[key], CollectMapEx::transformToLetter)
            .subscribe(Logger::onNext);
    }

    private static String transformToLetter(int value) {
        return Character.toString((char) ('a' + value));
    }
}
