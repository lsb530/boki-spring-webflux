package boki.learnkt.common

data class Member(
    private val id: Long? = null,
    private val email: String? = null,
    private val name: String? = null,
) {
    fun getEmail() = email
}
