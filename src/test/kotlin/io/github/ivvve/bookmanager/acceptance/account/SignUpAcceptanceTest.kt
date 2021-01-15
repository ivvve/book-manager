package io.github.ivvve.bookmanager.acceptance.account

import io.github.ivvve.bookmanager.acceptance.AcceptanceTest
import io.github.ivvve.bookmanager.acceptance.account.step.`이메일 인증번호 전송 요청에 성공한다`
import io.github.ivvve.bookmanager.acceptance.account.step.`이메일 인증번호 전송 요청을 보낸다`
import io.github.ivvve.bookmanager.acceptance.account.step.`회원가입 요청을 보낸다`
import io.github.ivvve.bookmanager.acceptance.account.step.`회원가입에 성공한다`
import org.junit.jupiter.api.Test

class SignUpAcceptanceTest : AcceptanceTest() {
    @Test
    fun `회원가입을 한다`() {
        // given
        val email = "devson@naver.com"
        val password = "devson1234"

        // when
        val emailVerificationResponse = `이메일 인증번호 전송 요청을 보낸다`(email)
        // then
        `이메일 인증번호 전송 요청에 성공한다`(emailVerificationResponse)

        // when
        // TODO get verification code
        val verificationCode = "000000"
        val signUpResponse = `회원가입 요청을 보낸다`(email, password, verificationCode)
        // then
        `회원가입에 성공한다`(signUpResponse)
    }
}