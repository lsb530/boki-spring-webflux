package boki.mvcbranchoffice

import boki.core.Book
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MvcBranchOfficeApplication

fun main(args: Array<String>) {
	val book = Book(bookId = 1L, name = "Spring Boot in Action", price = 5000)
	println(book)
	runApplication<MvcBranchOfficeApplication>(*args)
}
