package io.github.ivvve.bookmanager.domain.account.infrastructure.verification

import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class MemoryEmailVerificationCodeStore : VerificationCodeStore {
    private val store = ConcurrentHashMap<String, VerificationCode>()

    override fun save(code: VerificationCode): VerificationCode {
        this.store[code.email] = code
        return code
    }

    override fun findByEmail(email: String): VerificationCode? {
        return this.store[email]
    }

    override fun clear() {
        this.store.clear()
    }
}
