package boki.learnkt.common

data class Book(
    val bookId: Int,
    val bookName: String?,
    val authorName: String?,
    val penName: String?,
    val price: Int,
    val stockQuantity: Int,
)
