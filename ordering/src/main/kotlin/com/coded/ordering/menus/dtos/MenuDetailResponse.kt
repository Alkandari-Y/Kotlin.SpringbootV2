package com.coded.ordering.menus.dtos

import com.coded.ordering.restaurants.dtos.RestaurantInfoResponse
import com.coded.ordering.restaurants.dtos.toResponse
import com.coded.ordering.domain.entities.MenuEntity
import java.math.BigDecimal

data class MenuDetailResponse (
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val restaurant: RestaurantInfoResponse,
)

fun MenuEntity.toResponse() = MenuDetailResponse(
    id = id!!,
    name = name,
    price = price,
    restaurant = restaurant!!.toResponse(),
)