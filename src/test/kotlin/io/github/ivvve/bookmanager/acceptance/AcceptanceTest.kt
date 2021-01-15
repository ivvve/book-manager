package io.github.ivvve.bookmanager.acceptance

import io.github.ivvve.bookmanager.BookManagerApplication
import io.github.ivvve.bookmanager.domain.account.domain.account.AccountRepository
import io.github.ivvve.bookmanager.domain.account.domain.verification.VerificationCodeStore
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(classes = [BookManagerApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcceptanceTest {
    @LocalServerPort
    var port = 0

    @Autowired
    private lateinit var verificationCodeStore: VerificationCodeStore
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @BeforeAll
    fun setUp() {
        RestAssured.port = this.port
    }

    @AfterEach
    fun tearDown() {
        this.verificationCodeStore.clear()
        this.accountRepository.deleteAll()
    }
}