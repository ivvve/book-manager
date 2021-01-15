package io.github.ivvve.bookmanager.domain.account.usecase

import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeGenerator
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val verificationCodeGenerator: VerificationCodeGenerator,
    private val verificationCodeStore: VerificationCodeStore,
) {
    fun sendEmailVerificationCode(email: String): VerificationCode {
        val verificationCode = this.verificationCodeGenerator.generate(email)
        this.verificationCodeStore.save(verificationCode)
        return verificationCode
    }
}