package io.github.ivvve.bookmanager.domain.account.domain.verification

import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode

interface VerificationCodeSender {
    fun send(code: VerificationCode)
}