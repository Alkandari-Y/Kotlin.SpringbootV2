package com.coded.spring.ordering.auth

import com.coded.spring.ordering.auth.dtos.JwtResponseDto
import com.coded.spring.ordering.auth.dtos.LoginRequestDto
import com.coded.spring.ordering.users.UserService
import com.coded.spring.ordering.users.dtos.UserCreateRequestDto
import com.coded.spring.ordering.users.dtos.toEntity
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.ErrorResponse


@Tag(name="Auth Ppi")
@RestController
@RequestMapping("/api/v1/auth")
class AuthApiController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) {
    @Operation(summary = "User login endpoint to receive JWT token")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successful login",
            content = [
                Content(
                    schema = Schema(implementation = JwtResponseDto::class),
                    mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json")
            ]),
        )
    @PostMapping(path = ["/login"])
    fun login(@Valid @RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<*> {
        val authToken = UsernamePasswordAuthenticationToken(
            loginRequestDto.username,
            loginRequestDto.password
        )
        val authenticated = authenticationManager.authenticate(authToken)

        if (authenticated.isAuthenticated.not()) {
            throw UsernameNotFoundException("Invalid credentials")
        }

        val userDetails = userDetailsService.loadUserByUsername(loginRequestDto.username)
        val token = jwtService.generateToken(userDetails.username)
        return ResponseEntity(JwtResponseDto(token), HttpStatus.OK)
    }


    @Operation(summary = "Create a new user and receive a JWT token")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successful registration",
            content = [
                Content(
                    schema = Schema(implementation = JwtResponseDto::class),
                    mediaType = "application/json")
            ]),
        ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = [
                Content(mediaType = "application/json")
            ]),
    )
    @PostMapping(path = ["/register"])
    fun createUser(
        @Valid @RequestBody user: UserCreateRequestDto
    ): ResponseEntity<JwtResponseDto> {
        val userEntity = userService.createUser(
            user.copy(
                password = passwordEncoder.encode(
                    user.password
                )
            ).toEntity()
        )
        val token = jwtService.generateToken(userEntity.username)
        return ResponseEntity(JwtResponseDto(token), HttpStatus.OK)
    }
}