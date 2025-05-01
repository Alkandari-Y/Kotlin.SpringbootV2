package com.coded.ordering.providers

import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Named
class JwtAuthProvider (
    @Value("\${authService.url}")
    private val authServiceURL: String
){
    fun authenticateToken(token: String): ValidateTokenResponseDto {
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange<ValidateTokenResponseDto>(
            url = authServiceURL,
            method = HttpMethod.POST,
            requestEntity = HttpEntity<String>(
                MultiValueMap.fromMultiValue(mapOf("Authorization" to listOf("Bearer $token")))
            ),
            object : ParameterizedTypeReference<ValidateTokenResponseDto>() {
            }
        )
        return response.body ?: throw IllegalStateException("Check token response has no body ...")
    }
}

data class ValidateTokenResponseDto (
    val userId: Long
)