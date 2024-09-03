package boki.learnkt.util

import org.slf4j.LoggerFactory

object Logger {
    private val log = LoggerFactory.getLogger(Thread.currentThread().stackTrace[1].className)

    fun info(data: Any?) {
        log.info("{}", data)
    }

    fun info(msg: String?, data: Any?) {
        log.info(msg, data)
    }

    fun info(msg: String?, data1: Any?, data2: Any?) {
        log.info(msg, data1, data2)
    }

    fun doOnNext(data: Any?) {
        log.info("# doOnNext(): {}", data)
    }

    fun doOnNext(data1: Any?, data2: Any?) {
        log.info("# doOnNext(): {} {}", data1, data2)
    }

    fun doOnNext(taskName: String, operator: String, data: Any?) {
        log.info("# doOnNext() {} {}: {}", taskName, operator, data)
    }

    fun doOnSubscribe() {
        log.info("# doOnSubscribe()")
    }

    fun doFirst() {
        log.info("# doFirst()")
    }

    fun doFinally(data: Any?) {
        log.info("# doFinally(): {}", data)
    }

    fun doOnRequest(data: Any?) {
        log.info("# doOnRequest(): {}", data)
    }

    fun doOnComplete() {
        log.info("# doOnComplete()")
    }

    fun doOnTerminate(operator: String) {
        log.info("# doOnTerminate(): {}", operator)
    }

    fun doAfterTerminate(operator: String) {
        log.info("# doAfterTerminate() {}", operator)
    }

    fun onNext(data: Any?) {
        log.info("# onNext(): {}", data)
    }

    fun onNext(data1: Any?, data2: Any?) {
        log.info("# onNext(): {} : {}", data1, data2)
    }

    fun onNext(message: String, data1: Any?, data2: Any?) {
        log.info("# onNext() {}: {} {}", message, data1, data2)
    }

    fun onError(error: Throwable?) {
        log.error("error happened: ", error)
    }

    fun onComplete() {
        log.error("# onComplete()")
    }

    fun onComplete(data: Any?) {
        log.info("# onComplete(): {}", data)
    }

    fun onNext(message: String?, data: Any?) {
        log.info("# {} onNext(): {}", message, data)
    }

    fun filter(data: Any?) {
        log.info("# filter(): {}", data)
    }

    fun map(data: Any?) {
        log.info("# map(): {}", data)
    }
}