package io.github.ivvve.bookmanager.web.auth.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpRequest(
    @Email
    val email: String,
    @NotBlank
    @Size(min=5, max=20)
    val password: String,
    @NotBlank
    @Size(min=5, max=5)
    val code: String
)
