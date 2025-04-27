package com.coded.spring.ordering.test

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.repositories.UserRepository
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.assertEquals

class HelloWorldApiSteps {

    @Inject
    lateinit var testRestTemplate: TestRestTemplate
    @Inject
    lateinit var  userRepository: UserRepository
    @Inject
    lateinit var  passwordEncoder: PasswordEncoder

    private var response: ResponseEntity<String>? = null
    private var loginToken: String? = null

    @When("I make a GET request to {string}")
    fun makeRequestToHelloWorld(uri: String) {

        val user = userRepository.createUser(passwordEncoder)
        val jwtResponse = testRestTemplate.postForEntity(
            AUTH_LOGIN_API,
            user,
            JwtResponseDto::class.java,
        )

        assertEquals(200, jwtResponse.statusCode.value())
        assertNotNull(jwtResponse.body)
        loginToken = jwtResponse.body?.token

        val httpEntity = createHttpRequest<Void>(loginToken as String)

        response = testRestTemplate.exchange(
            uri,
            HttpMethod.GET,
            httpEntity,
            String::class.java
        )
    }
//
    @Then("I get the Http status code should be {int}")
    fun getStatusCode200(statusCode: Int) {
        assertEquals(HttpStatus.OK, response?.statusCode)
    }
//
//    @And("the response body should be {string}")
//    fun getHelloWorldResponse(body: String) {
//        assertEquals("Hello World!", response?.body)
//    }
}