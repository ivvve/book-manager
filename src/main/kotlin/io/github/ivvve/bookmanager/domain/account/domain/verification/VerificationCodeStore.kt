package io.github.ivvve.bookmanager.domain.account.domain.verification

import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode

interface VerificationCodeStore {
    fun save(code: VerificationCode): VerificationCode

    fun findByEmail(email: String): VerificationCode?
}