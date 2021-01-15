package io.github.ivvve.bookmanager.domain.account.domain.verification.entities

class VerificationCode(
    val email: String,
    val code: String
) {
    fun isMatchedWith(code: VerificationCode): Boolean {
        return (this.email == code.email) and (this.code == code.code)
    }

    fun isNotMatchedWith(code: VerificationCode): Boolean {
        return !this.isMatchedWith(code)
    }
}
