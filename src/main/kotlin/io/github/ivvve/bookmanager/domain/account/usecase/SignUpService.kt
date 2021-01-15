package io.github.ivvve.bookmanager.domain.account.usecase

import io.github.ivvve.bookmanager.domain.account.domain.account.AccountRepository
import io.github.ivvve.bookmanager.domain.account.domain.account.DuplicatedAccountChecker
import io.github.ivvve.bookmanager.domain.account.domain.account.EmailValidator
import io.github.ivvve.bookmanager.domain.account.domain.account.entities.Account
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeGenerator
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeSender
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeValidator
import io.github.ivvve.bookmanager.domain.account.domain.verification.entities.VerificationCode
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignUpService(
    private val emailValidator: EmailValidator,
    private val verificationCodeGenerator: VerificationCodeGenerator,
    private val verificationCodeStore: VerificationCodeStore,
    private val verificationCodeValidator: VerificationCodeValidator,
    private val verificationCodeSender: VerificationCodeSender,
    private val duplicatedAccountChecker: DuplicatedAccountChecker,
    private val accountRepository: AccountRepository
) {
    fun sendEmailVerificationCode(email: String): VerificationCode {
        this.emailValidator.validate(email)

        val verificationCode = this.verificationCodeGenerator.generate(email)
        this.verificationCodeStore.save(verificationCode)
        this.verificationCodeSender.send(verificationCode)

        return verificationCode
    }

    fun signUp(email: String, password: String, verificationCode: String): Account {
        this.verificationCodeValidator.validate(VerificationCode(email, verificationCode))

        // TODO encrypt password
        val account = Account(UUID.randomUUID(), email, password)
        this.duplicatedAccountChecker.check(account)
        return this.accountRepository.save(account)
    }
}