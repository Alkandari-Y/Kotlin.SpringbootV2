package com.coded.spring.ordering.test

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
class HelloWorldApiTests