package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull


data class RestaurantCreateRequestDto(
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 3, message = "Name is too short")
    val name: String
)

fun RestaurantCreateRequestDto.toEntity() = RestaurantEntity(name = name)