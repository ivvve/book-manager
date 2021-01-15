package io.github.ivvve.bookmanager.domain.account.domain.account.entities

import java.util.*

class Account(
    val id: UUID,
    val email: String,
    val password: String,
) {
    var active: Boolean = true

    fun signOut() {
        active = false
    }
}