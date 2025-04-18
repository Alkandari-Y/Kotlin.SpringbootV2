package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.UserEntity
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


data class UserCreateRequestDto(
    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:NotBlank(message = "Email is required")
    val username: String,

    @field:NotBlank(message = "Password is required")
    @field:Email(message = "Email is too short")
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 6, message = "Password is too short")
    @field:Pattern(regexp = """(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).*""", message = "Password is too simple")
    val password: String
)

fun UserCreateRequestDto.toEntity() = UserEntity(
    name = name,
    username = username,
    email = email,
    password = password
)
