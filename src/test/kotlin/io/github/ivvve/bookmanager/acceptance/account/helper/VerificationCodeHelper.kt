package io.github.ivvve.bookmanager.acceptance.account.helper

import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VerificationCodeHelper {
    @Autowired
    private lateinit var verificationCodeStore: VerificationCodeStore

    fun getVerificationCode(email: String): VerificationCode {
        return this.verificationCodeStore.findByEmail(email)!!
    }

    fun setVerificationCode(email: String, code: String) {
        this.verificationCodeStore.save(VerificationCode(email, code))
    }
}