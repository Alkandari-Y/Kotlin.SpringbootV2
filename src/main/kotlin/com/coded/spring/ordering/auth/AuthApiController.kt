package com.coded.spring.ordering.auth

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.auth.dtos.LoginRequestDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthApiController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
) {
    @PostMapping(path=["/login"])
    fun login(@Valid @RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<*> {
        val authToken = UsernamePasswordAuthenticationToken(loginRequestDto.username, loginRequestDto.password)
        val authenticated = authenticationManager.authenticate(authToken)

        if (authenticated.isAuthenticated.not()) {
            throw UsernameNotFoundException("Invalid credentials")
        }

        val userDetails = userDetailsService.loadUserByUsername(loginRequestDto.username)
        val token = jwtService.generateToken(userDetails.username)
        return ResponseEntity(JwtResponseDto(token), HttpStatus.OK)
    }
}