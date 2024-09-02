package boki.learnjava.v3_transformsequence.c_flatmapext;

import boki.learnjava.common.SampleData;
import boki.learnjava.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

/**
 * flatMapIterable 기본 개념 예제
 *  - Iterable로 emit 된 데이터를 순차적으로 평탄화 한다.
 *  - 유형별 코로나 백신 list 를 평탄화 하는 예제
 */
public class FlatMapIterableEx {
    public static void main(String[] args) {
        Flux.just(getViralVectorVaccines(), getmRNAVaccines(), getSubunitVaccines())
            // .map(vaccines -> vaccines)
            .flatMapIterable(vaccines -> vaccines)
            .subscribe(Logger::onNext);
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getViralVectorVaccines() {
        return SampleData.viralVectorVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getmRNAVaccines() {
        return SampleData.mRNAVaccines;
    }

    private static List<Tuple2<SampleData.CoronaVaccine, Integer>> getSubunitVaccines() {
        return SampleData.subunitVaccines;
    }
}