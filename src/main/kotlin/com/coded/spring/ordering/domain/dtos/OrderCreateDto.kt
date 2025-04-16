package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.*

data class OrderCreateDto(
    val user: UserEntity,
    val restaurant: RestaurantEntity,
    val items: List<OrderItemCreateDto>
)

fun OrderCreateDto.toEntity(): OrderEntity = OrderEntity(
    user = user,
    restaurant = restaurant
)