package com.coded.spring.ordering.restaurants.dtos

import com.coded.spring.ordering.domain.entities.RestaurantEntity

data class RestaurantInfoResponse(
    val id: Long,
    val name: String
)

fun RestaurantEntity.toResponse() = RestaurantInfoResponse(
    id=id!!,
    name=name
)