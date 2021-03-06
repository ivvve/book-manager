package io.github.ivvve.bookmanager.acceptance.account

import io.github.ivvve.bookmanager.acceptance.AcceptanceTest
import io.github.ivvve.bookmanager.acceptance.account.helper.AccountHelper
import io.github.ivvve.bookmanager.acceptance.account.helper.VerificationCodeHelper
import io.github.ivvve.bookmanager.acceptance.account.step.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SignUpAcceptanceTest : AcceptanceTest() {
    @Autowired
    private lateinit var accountHelper: AccountHelper
    @Autowired
    private lateinit var verificationCodeHelper: VerificationCodeHelper

    @Test
    fun `회원가입을 한다`() {
        // given
        val email = this.accountHelper.getEmail("devson")
        val password = "devson1234"

        // when
        val emailVerificationResponse = `이메일 인증번호 전송 요청을 보낸다`(email)
        // then
        `이메일 인증번호 전송 요청에 성공한다`(emailVerificationResponse)

        // when
        val verificationCode = this.verificationCodeHelper.getVerificationCode(email)
        val signUpResponse = `회원가입 요청을 보낸다`(email, password, verificationCode.code)
        // then
        `회원가입에 성공한다`(signUpResponse)
    }

    @Test
    fun `이메일 인증번호를 틀리면 회원가입에 실패한다`() {
        // given
        val email = this.accountHelper.getEmail("devson")
        val password = "devson1234"

        // when
        val emailVerificationResponse = `이메일 인증번호 전송 요청을 보낸다`(email)
        // then
        `이메일 인증번호 전송 요청에 성공한다`(emailVerificationResponse)

        // 저장된 verification code를 변경
        val verificationCode = this.verificationCodeHelper.getVerificationCode(email)
        this.verificationCodeHelper.setVerificationCode(email, verificationCode.code + "X")

        // when
        val signUpResponse = `회원가입 요청을 보낸다`(email, password, verificationCode.code)
        // then
        `이메일 인증번호가 틀려 회원가입에 실패한다`(signUpResponse)
    }

    @Test
    fun `사내 도메인이 아닌 email로 이메일 인증번호 전송에 실패한다`() {
        // given
        val email = this.accountHelper.getEmail("devson") + "a"

        // when
        val emailVerificationResponse = `이메일 인증번호 전송 요청을 보낸다`(email)
        // then
        `이메일 인증번호 전송 요청에 실패한다`(emailVerificationResponse)
    }

    @Test
    fun `이미 가입한 email로 회원가입을 하는 경우 실패한다`() {
        // given
        val email = this.accountHelper.getEmail("devson")
        val password = "devson1234"
        this.accountHelper.newAccount(email, password)

        `이메일 인증번호 전송 요청을 보낸다`(email)
        val verificationCode = this.verificationCodeHelper.getVerificationCode(email)
        `회원가입 요청을 보낸다`(email, password, verificationCode.code)

        // when
        val signUpResponse = `회원가입 요청을 보낸다`(email, password, verificationCode.code)

        // then
        `이메일 중복으로 회원가입에 실패한다`(signUpResponse)
    }
}