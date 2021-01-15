package io.github.ivvve.bookmanager.domain.account.domain.verification

import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class VerificationCodeGenerator {
    fun generate(email: String): VerificationCode {
        val verificationCode = this.generateCode()
        return VerificationCode(email, verificationCode)
    }

    private fun generateCode(): String {
        val verificationCode = StringBuilder()

        for (i in 1..6) {
            verificationCode.append(Random.nextInt(10))
        }

        return verificationCode.toString()
    }
}