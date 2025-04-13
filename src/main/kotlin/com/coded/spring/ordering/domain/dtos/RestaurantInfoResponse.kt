package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.Restaurant

data class RestaurantInfoResponse(
    val id: Long,
    val name: String
)

fun Restaurant.toResponse() = RestaurantInfoResponse(
    id=id!!,
    name=name
)