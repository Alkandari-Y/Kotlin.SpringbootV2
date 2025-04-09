package com.coded.spring.ordering.domain.requests

data class OrderCreateRequestDto(
    val user: String,
    val restaurant: String,
    val items: List<String>
)