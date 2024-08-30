package boki.learnjava.v3_transformsequence.a_map;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;

/**
 * map 활용 예제
 * - Morse Code를 알파벳으로 변환하는 예제
 */
public class MapEx3 {
    public static void main(String[] args) {
        Flux
            .just("...", "---", "...")
            .map(MapEx3::transformMorseCode)
            .subscribe(Logger::onNext);
    }

    private static String transformMorseCode(String morseCode) {
        return SampleData.morseCodeMap.get(morseCode);
    }
}
