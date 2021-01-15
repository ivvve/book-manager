package io.github.ivvve.bookmanager.domain.account.domain.account

import io.github.ivvve.bookmanager.domain.account.domain.account.entities.Account
import java.util.*

interface AccountRepository {
    fun save(account: Account): Account

    fun findById(id: UUID): Account?
}