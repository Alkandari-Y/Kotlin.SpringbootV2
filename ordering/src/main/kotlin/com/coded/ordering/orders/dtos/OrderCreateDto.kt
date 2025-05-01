package com.coded.ordering.orders.dtos

import com.coded.ordering.domain.entities.*

data class OrderCreateDto(
    val user: Long,
    val restaurant: RestaurantEntity,
    val items: List<OrderItemCreateDto>
)

fun OrderCreateDto.toOrderEntity(): OrderEntity = OrderEntity(
    userId = user,
    restaurant = restaurant
)