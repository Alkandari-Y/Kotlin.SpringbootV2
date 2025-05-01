package com.coded.ordering.restaurants.dtos

import com.coded.ordering.domain.entities.RestaurantEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class RestaurantCreateRequestDto(
    @field:NotBlank(message = "Name is required")
    @field:Size(min = 3, message = "Name is too short")
    val name: String
)

fun RestaurantCreateRequestDto.toEntity() = RestaurantEntity(name = name)