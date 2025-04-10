package com.coded.spring.ordering.domain.dtos

data class OrderItemCreateDto(
    val itemId: Long,
    val quantity: Int,
)