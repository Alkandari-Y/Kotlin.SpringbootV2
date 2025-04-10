package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.entities.User

data class OrderCreateDto(
    val user: User,
    val restaurant: Restaurant,
    val items: List<OrderItemCreateDto>
)

fun OrderCreateDto.toEntity(): Order = Order(
    user = user,
    restaurant = restaurant
)