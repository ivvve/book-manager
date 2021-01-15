package io.github.ivvve.bookmanager.domain.account.domain.verification

import io.github.ivvve.bookmanager.common.annotations.DomainService
import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import io.github.ivvve.bookmanager.domain.account.domain.verification.exceptions.VerificationCodeNotFoundException
import io.github.ivvve.bookmanager.domain.account.domain.verification.exceptions.VerificationNotMatchedException

@DomainService
class VerificationCodeValidator(
    private val verificationCodeStore: VerificationCodeStore
) {
    fun validate(code: VerificationCode) {
        val storedCode = this.verificationCodeStore.findByEmail(code.email)

        if (storedCode == null) {
            throw VerificationCodeNotFoundException()
        }

        if (storedCode.isNotMatchedWith(code)) {
            throw VerificationNotMatchedException()
        }
    }
}