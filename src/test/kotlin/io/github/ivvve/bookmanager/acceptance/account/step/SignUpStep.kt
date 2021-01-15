package io.github.ivvve.bookmanager.acceptance.account.step

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.HttpStatus

fun `이메일 인증번호 전송 요청을 보낸다`(email: String): ExtractableResponse<Response> {
    val signUpParams = mapOf(Pair("email", email))

    return RestAssured
        .given().log().all().body(signUpParams).contentType(ContentType.JSON)
        .`when`().post("/auth/verification-code")
        .then().log().all()
        .extract()
}

fun `이메일 인증번호 전송 요청에 성공한다`(emailVerificationResponse: ExtractableResponse<Response>) {
    assertThat(emailVerificationResponse.statusCode()).isEqualTo(HttpStatus.OK.value())
}

fun `이메일 인증번호 전송 요청에 실패한다`(emailVerificationResponse: ExtractableResponse<Response>) {
    assertThat(emailVerificationResponse.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value())
}

fun `회원가입 요청을 보낸다`(email: String, password: String, verificationCode: String): ExtractableResponse<Response> {
    val signUpParams = mapOf(
        Pair("email", email),
        Pair("password", password),
        Pair("code", verificationCode)
    )

    return RestAssured
        .given().log().all().body(signUpParams).contentType(ContentType.JSON)
        .`when`().post("/auth/sign-up")
        .then().log().all()
        .extract()
}

fun `회원가입에 성공한다`(signUpResponse: ExtractableResponse<Response>) {
    assertThat(signUpResponse.statusCode()).isEqualTo(HttpStatus.OK.value())
}

fun `이메일 인증번호가 틀려 회원가입에 실패한다`(signUpResponse: ExtractableResponse<Response>) {
    assertThat(signUpResponse.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value())
}

fun `이메일 중복으로 회원가입에 실패한다`(signUpResponse: ExtractableResponse<Response>) {
    assertThat(signUpResponse.statusCode()).isEqualTo(HttpStatus.CONFLICT.value())
}
