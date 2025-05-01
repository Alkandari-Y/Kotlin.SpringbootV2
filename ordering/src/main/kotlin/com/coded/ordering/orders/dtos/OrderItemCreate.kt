package com.coded.ordering.orders.dtos

data class OrderItemCreateDto(
    val itemId: Long,
    val quantity: Int,
)