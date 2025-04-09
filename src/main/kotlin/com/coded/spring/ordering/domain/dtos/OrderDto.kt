package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.OrderItem
import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.entities.User

data class OrderDto(
    val user: User,
    val restaurant: Restaurant,
    val items: List<String>
)