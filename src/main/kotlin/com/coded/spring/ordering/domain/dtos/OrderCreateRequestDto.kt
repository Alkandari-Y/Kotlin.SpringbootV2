package com.coded.spring.ordering.domain.dtos

data class OrderCreateRequestDto(
    val user: String,
    val restaurant: String,
    val items: List<String>
)