package io.github.ivvve.bookmanager.domain.account.infrastructure.account

import io.github.ivvve.bookmanager.domain.account.domain.account.entities.Account
import io.github.ivvve.bookmanager.domain.account.domain.account.AccountRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Repository
class MemoryAccountRepository : AccountRepository {
    private val store = ConcurrentHashMap<String, Account>()

    override fun save(account: Account): Account {
        this.store[account.id.toString()] = account
        return account
    }

    override fun findById(id: UUID): Account? {
        return this.store[id.toString()]
    }
}