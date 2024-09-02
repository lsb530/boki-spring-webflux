package boki.learnjava.v3_transformsequence.i_collect;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * collectList 활용 예제
 * - emit된 세 개의 모스 부호를 List<String> 타입으로 Downstream에 emit한다.
 */
public class CollectListEx {
    public static void main(String[] args) {
        Flux
            .just("...", "---", "...")
            .map(CollectListEx::transformMorseCode)
            .collectList() // 반환형: Mono
            .subscribe(list -> Logger.onNext(String.join("", list)));
    }

    public static String transformMorseCode(String morseCode) {
        return SampleData.morseCodeMap.get(morseCode);
    }
}
