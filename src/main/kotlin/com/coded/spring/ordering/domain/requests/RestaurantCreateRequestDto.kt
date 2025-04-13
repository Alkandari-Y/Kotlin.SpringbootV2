package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.Restaurant
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull


data class RestaurantCreateRequestDto(
    @field:NotBlank(message = "Name is required")
    @field:NotNull
    @field:Length(max = 3, message = "Name is too short")
    val name: String
)

fun RestaurantCreateRequestDto.toEntity() = Restaurant(name = name)