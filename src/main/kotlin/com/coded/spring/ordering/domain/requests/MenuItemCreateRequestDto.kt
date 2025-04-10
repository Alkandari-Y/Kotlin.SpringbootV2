package com.coded.spring.ordering.domain.requests

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.entities.Restaurant

data class MenuCreateRequestDto(
    val name: String,
    val restaurantId: Long,
    val price: Double
)

fun MenuCreateRequestDto.toEntity(restaurant: Restaurant): Menu = Menu(
    name=name,
    restaurant=restaurant,
    price=price
)