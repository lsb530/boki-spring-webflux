package boki.learnjava.debug.logop;

import boki.learnjava.util.Logger;
import boki.learnjava.util.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * log() operator Custom Category 를 사용하는 예제
 */
public class LogOperationEx3 {

    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
            .subscribeOn(Schedulers.boundedElastic())
            .log("Fruit.Source")
            .publishOn(Schedulers.parallel())
            .map(String::toLowerCase)
            .log("Fruit.Lower")
            .map(fruit -> fruit.substring(0, fruit.length() - 1))
            .log("Fruit.Substring")
            .map(fruits::get)
            .log("Fruit.Name")
            .subscribe(Logger::onNext, Logger::onError);

        TimeUtils.sleep(100L);
    }
}