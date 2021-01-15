package io.github.ivvve.bookmanager.domain.account.domain.account

import io.github.ivvve.bookmanager.common.annotations.DomainService
import io.github.ivvve.bookmanager.domain.account.domain.account.entities.Account
import io.github.ivvve.bookmanager.domain.account.domain.account.exceptions.DuplicatedEmailException

@DomainService
class DuplicatedAccountChecker(
    private val accountRepository: AccountRepository
) {
    fun check(account: Account) {
        val account = this.accountRepository.findByEmail(account.email)

        if (account == null) {
            throw DuplicatedEmailException()
        }
    }
}
