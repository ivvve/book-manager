package io.github.ivvve.bookmanager.acceptance.account.helper

import io.github.ivvve.bookmanager.acceptance.account.step.`이메일 인증번호 전송 요청을 보낸다`
import io.github.ivvve.bookmanager.acceptance.account.step.`회원가입 요청을 보낸다`
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AccountHelper {
    @Autowired
    private lateinit var verificationCodeStore: VerificationCodeStore
    @Value("\${app.service.email-domain}")
    private lateinit var emailDomains: Array<String>

    fun newAccount(email: String, password: String) {
        `이메일 인증번호 전송 요청을 보낸다`(email)
        val verificationCode = this.verificationCodeStore.findByEmail(email)!!
        `회원가입 요청을 보낸다`(email, password, verificationCode.code)
    }

    fun getEmail(username: String): String {
        return "$username@${emailDomains[0]}"
    }
}