package boki.learnkt.v6_error.a_error

import boki.learnkt.common.Member
import boki.learnkt.util.Logger
import reactor.core.publisher.Mono

/**
 * error Operator 활용 예제 코드
 * - 입력으로 전달 받은 데이터의 유효성 검증에 실패할 경우 onError signal을 전송하는 예제
 */
const val EXIST_EMAIL = "kevin@gmail.com"
const val NOT_EXIST_EMAIL = "tom@gmail.com"

fun main() {
    verifyExistMember(EXIST_EMAIL)
    // verifyExistMember(NOT_EXIST_EMAIL)
        .flatMap { _ -> saveMember(
            Member(id = 1L, email = NOT_EXIST_EMAIL, name = "boki")
        ) }
        .subscribe(
            { createMember -> Logger.onNext("created member", createMember.getEmail()) },
            { Logger.onError(it) },
            { Logger.onComplete() }
        )
}

private fun verifyExistMember(email: String): Mono<Member> {
    return selectFromMemberWhere(email)
        .switchIfEmpty(Mono.just(Member())) // saveMember()를 실행시킬 수 있도록 비어있는 member를 리턴한다.
        .flatMap { foundMember ->
            if (foundMember?.getEmail() != null) {
                return@flatMap Mono.error(RuntimeException("Member exists"))
            }
            return@flatMap Mono.just(foundMember)
        }
}

private fun selectFromMemberWhere(email: String): Mono<Member> {
    // 파라미터로 전달 받은 email로 DB에 조회한걸로 가정.
    Logger.info("# select from member where email=$email")
    val existMember = Mono.just(Member(id = 1L, email = "kevin@gmail.com", name = "Kevin"))

    return if (email == EXIST_EMAIL) existMember else Mono.empty()
}

private fun saveMember(member: Member): Mono<Member> {
    // DB에 저장한걸로 가정
    Logger.info("# insert into member...")
    return Mono.just(member)
}