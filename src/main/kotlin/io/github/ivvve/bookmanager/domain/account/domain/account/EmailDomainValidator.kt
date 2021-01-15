package io.github.ivvve.bookmanager.domain.account.domain.account

import io.github.ivvve.bookmanager.domain.account.domain.account.exceptions.NotSupportedEmailException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmailDomainValidator(
    @Value("\${app.service.email-domain}") private val supportedEmailDomains: Array<String>
) {
    private val emailDelimiters = "@"

    fun validate(email: String) {
        val emailDomain = email.split(emailDelimiters)[1]

        if (emailDomain !in supportedEmailDomains) {
            throw NotSupportedEmailException()
        }
    }
}