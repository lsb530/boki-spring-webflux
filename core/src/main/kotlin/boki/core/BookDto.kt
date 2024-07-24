package boki.core

data class BookRequest(
    val name: String,
    val price: Int,
)

data class BookResponse(
    val bookId: Long,
    val name: String,
    val price: Int,
)
