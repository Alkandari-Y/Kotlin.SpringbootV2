package com.coded.spring.ordering.profiles.dtos

import com.coded.spring.ordering.domain.entities.ProfileEntity
import jakarta.validation.constraints.*

data class ProfileCreateRequestDto(
    @field:NotBlank
    @field:Size(min = 3, max = 100)
    @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only letters")
    val firstName: String,

    @field:NotBlank
    @field:Size(min = 3, max = 100)
    @field:Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only letters")
    val lastName: String,

    @field:NotBlank
    @field:Size(min = 7, max = 12)
    @field:Pattern(regexp = "^\\d{7,12}$", message = "Phone number must be digits only and 7-12 characters long")
    val phoneNumber: String,
)


fun ProfileCreateRequestDto.toEntity() = ProfileEntity(
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
)