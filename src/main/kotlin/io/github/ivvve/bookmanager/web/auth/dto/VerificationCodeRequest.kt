package io.github.ivvve.bookmanager.web.auth.dto

import javax.validation.constraints.Email

data class VerificationCodeRequest(
    @Email
    val email: String
)