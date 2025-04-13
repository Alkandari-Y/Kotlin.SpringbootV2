package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.Menu
import java.math.BigDecimal

data class MenuDetailResponse (
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val restaurant: RestaurantInfoResponse,
)

fun Menu.toResponse() = MenuDetailResponse(
    id = id!!,
    name = name,
    price = price,
    restaurant = restaurant!!.toResponse(),
)