package com.coded.spring.ordering.domain

import com.coded.spring.ordering.domain.dtos.OrderCreateRequestDto
import com.coded.spring.ordering.domain.dtos.OrderDto
import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.entities.OrderItem

fun OrderCreateRequestDto.toEntity() =
    Order(
        user=this.user,
        restaurant = this.restaurant,
        items = this.items.map { OrderItem(product = it) }
    )

fun Order.toDto() = OrderDto(
    user=this.user,
    restaurant=this.restaurant,
    items=this.items.map { it.product }
)