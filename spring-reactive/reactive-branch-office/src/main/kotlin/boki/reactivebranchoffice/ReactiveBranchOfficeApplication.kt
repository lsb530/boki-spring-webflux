package boki.reactivebranchoffice

import boki.core.BookResponse
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ReactiveBranchOfficeApplication {
	@Bean
	fun bookMap(): MutableMap<Long, BookResponse> {
		val bookMap: MutableMap<Long, BookResponse> = HashMap()
		for (i in 1..2000000) {
			bookMap[i.toLong()] = BookResponse(i.toLong(), "IT Book$i", 2000)
		}

		return bookMap
	}
}

fun main(args: Array<String>) {
	runApplication<ReactiveBranchOfficeApplication>(*args)
}
