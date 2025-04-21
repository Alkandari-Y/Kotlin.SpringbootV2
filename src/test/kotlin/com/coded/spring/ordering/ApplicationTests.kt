package com.coded.spring.ordering

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.auth.dtos.LoginRequestDto
import com.coded.spring.ordering.users.dtos.UserCreateRequestDto
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import kotlin.test.assertEquals

const val HELLO_WORLD_API = "/api/v1/hello-world"
const val AUTH_LOGIN_API = "/api/v1/auth/login"
const val AUTH_REGISTER_API = "/api/v1/auth/register"

val USER_LOGIN_CREDENTIALS = LoginRequestDto("admin", "passwordTest123")
val USER_REGISTER_REQUEST = UserCreateRequestDto("admin", "admin", "admin@example.com", "passwordTest123")

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests
	@Autowired constructor(
		var testRestTemplate: TestRestTemplate
	)
{


	@Test
	fun `init user`() {
		val result = testRestTemplate.postForEntity(
			AUTH_REGISTER_API,
			USER_REGISTER_REQUEST,
			JwtResponseDto::class.java,
		)

		println(result.body?.token)
	}

	@Test
	fun `test hello world returned`() {
		val loginToken = login()

		assertNotNull(loginToken)

		val expected = "Hello World!"

		val headers = HttpHeaders()
		headers.set("Authorization", "Bearer $loginToken")
		val httpEntity = HttpEntity<String>(headers)


		val response = testRestTemplate.exchange(
			HELLO_WORLD_API,
			HttpMethod.GET,
			httpEntity,
			String::class.java
		)

		assertEquals(expected, response.body)
		assertEquals(200, response.statusCode.value())
	}

	fun login(): String? {
		val result = testRestTemplate.postForEntity(
			AUTH_LOGIN_API,
			USER_LOGIN_CREDENTIALS,
			JwtResponseDto::class.java,
		)
		return result.body?.token
	}
}
