package io.github.ivvve.bookmanager.web.auth

import io.github.ivvve.bookmanager.domain.account.usecase.SignUpService
import io.github.ivvve.bookmanager.web.auth.dto.SignUpRequest
import io.github.ivvve.bookmanager.web.auth.dto.VerificationCodeRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class SignUpController(
    private val signUpService: SignUpService
) {
    @PostMapping("verification-code")
    fun sendVerificationCode(@RequestBody request: VerificationCodeRequest): ResponseEntity<Void> {
        this.signUpService.sendEmailVerificationCode(request.email)
        return ResponseEntity.ok().build()
    }

    @PostMapping("sign-up")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<Void> {
        this.signUpService.signUp(request.email, request.password, request.code)
        return ResponseEntity.ok().build()
    }
}