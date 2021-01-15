package io.github.ivvve.bookmanager.acceptance

import io.github.ivvve.bookmanager.BookManagerApplication
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(classes = [BookManagerApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcceptanceTest {
    @LocalServerPort
    var port = 0

    @BeforeAll
    fun setUp() {
        RestAssured.port = this.port
    }
}