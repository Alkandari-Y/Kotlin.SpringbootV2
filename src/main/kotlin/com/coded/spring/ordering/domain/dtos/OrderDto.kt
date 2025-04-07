package com.coded.spring.ordering.domain.dtos

data class OrderDto(
    val user: String,
    val restaurant: String,
    val items: List<String>
)