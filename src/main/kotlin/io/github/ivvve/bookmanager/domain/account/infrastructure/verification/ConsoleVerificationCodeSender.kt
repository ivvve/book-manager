package io.github.ivvve.bookmanager.domain.account.infrastructure.verification

import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeSender
import org.springframework.stereotype.Component

@Component
class ConsoleVerificationCodeSender : VerificationCodeSender {
    override fun send(code: VerificationCode) {
        println("Sent VerificationCode(${code.code}) to ${code.email}")
    }
}