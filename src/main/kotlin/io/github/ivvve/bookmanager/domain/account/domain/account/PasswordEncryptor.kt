package io.github.ivvve.bookmanager.domain.account.domain.account

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncryptor {
    fun encrypt(rawPassword: String): String {
        return BCryptPasswordEncoder().encode(rawPassword)
    }
}