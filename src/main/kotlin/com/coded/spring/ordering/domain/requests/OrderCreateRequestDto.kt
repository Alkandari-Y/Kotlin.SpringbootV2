package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.dtos.OrderCreateDto
import com.coded.spring.ordering.domain.dtos.OrderItemCreateDto
import com.coded.spring.ordering.domain.entities.*

data class OrderItemCreateRequestDto (
    val itemId: Long,
    val quantity: Int,
)

data class OrderCreateRequestDto(
    val userId: Long,
    val restaurantId: Long,
    val items: List<OrderItemCreateRequestDto>,
)

fun OrderCreateRequestDto.toCreateDto(
    user: User,
    restaurant: Restaurant,
    items: List<OrderItemCreateDto>,
) =  OrderCreateDto(user=user, restaurant=restaurant, items=items)

fun OrderItemCreateRequestDto.toCreateDto(
) = OrderItemCreateDto(itemId=itemId, quantity=quantity)