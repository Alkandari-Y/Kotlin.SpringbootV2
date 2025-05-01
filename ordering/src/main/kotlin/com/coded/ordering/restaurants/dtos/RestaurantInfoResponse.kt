package com.coded.ordering.restaurants.dtos

import com.coded.ordering.domain.entities.RestaurantEntity

data class RestaurantInfoResponse(
    val id: Long,
    val name: String
)

fun RestaurantEntity.toResponse() = RestaurantInfoResponse(
    id=id!!,
    name=name
)