package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.Restaurant


data class RestaurantCreateRequestDto(
    val name: String
)

fun RestaurantCreateRequestDto.toEntity() = Restaurant(name = name)