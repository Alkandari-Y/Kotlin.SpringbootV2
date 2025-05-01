package com.coded.ordering.orders.dtos

import com.coded.ordering.domain.entities.MenuEntity


data class ItemResponse (
    val id: Long,
    val name: String,
)

data class OrderItemResponse (
    val id : Long,
    val item: ItemResponse,
    val quantity: Int,
)

data class OrderCreateResponse (
    val id: Long,
    val userId: Long,
    val restaurantId: Long,
    val items: List<OrderItemResponse>,
)


fun MenuEntity.toItemResponse() = ItemResponse(
    id = id!!,
    name = name
)
