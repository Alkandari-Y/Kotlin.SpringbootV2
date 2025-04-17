package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.ProfileEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ProfileCreateRequestDto(
    @field:NotBlank
    @field:Size(min = 3, max = 100)
    @field:Pattern(regexp = """(?=.*[A-Za-z]).*""", message = "Name should contain only letters")
    val firstName: String,

    @field:NotBlank
    @field:Size(min = 3, max = 100)
    @field:Pattern(regexp = """(?=.*[A-Za-z]).*""", message = "Name should contain only letters")
    val lastName: String,

    @field:NotBlank
    @field:Size(min = 7, max = 12)
    @field:Pattern(regexp = """(?=.*\d).*""", message = "Phone number should contain only letters")
    val phoneNumber: String,

    @field:NotNull
    val userId: Long
)


fun ProfileCreateRequestDto.toEntity() = ProfileEntity(
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
)