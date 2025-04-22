package com.coded.spring.ordering

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.auth.dtos.LoginRequestDto
import com.coded.spring.ordering.repositories.UserRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.assertEquals
import kotlin.test.assertNull

const val HELLO_WORLD_API = "/api/v1/hello-world"
const val AUTH_LOGIN_API = "/api/v1/auth/login"
val USER_LOGIN_CREDENTIALS = LoginRequestDto("admin", "passwordTest123")

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests
@Autowired constructor(
    var testRestTemplate: TestRestTemplate
) {
    var loginToken: String? = null

    @BeforeEach
    fun `login user`() {
        val response = testRestTemplate.postForEntity(
            AUTH_LOGIN_API,
            USER_LOGIN_CREDENTIALS,
            JwtResponseDto::class.java,
        )
        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        loginToken = response.body?.token
    }

    @Test
    fun `test hello world returned and HTTP Status 200`() {
        val expected = "Hello World!"
        val httpEntity = createHttpRequest<Void>(loginToken as String)

        val response = testRestTemplate.exchange(
            HELLO_WORLD_API,
            HttpMethod.GET,
            httpEntity,
            String::class.java
        )

        assertEquals(expected, response.body)
        assertEquals(200, response.statusCode.value())
    }

    @Test
    fun `test hello world returns null and HTTP Status 403`() {
        val result = testRestTemplate.getForEntity(HELLO_WORLD_API, String::class.java)
        assertEquals(HttpStatus.FORBIDDEN.value(), result.statusCode.value())
        assertNull(result.body)
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(
            @Autowired userRepository: UserRepository,
            @Autowired passwordEncoder: PasswordEncoder
        ) {
            userRepository.createUser(passwordEncoder)
        }

        @JvmStatic
        @AfterAll
        fun tearDown(
            @Autowired userRepository: UserRepository
        ) {
            userRepository.deleteAll()
        }
    }
}
