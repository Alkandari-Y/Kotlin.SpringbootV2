package com.coded.spring.ordering.domain.projections

import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.entities.User

data class ItemResponse(
    val id: Long,
    val name: String,
)

data class OrderItemResponse(
    val item: ItemResponse,
    val quantity: Int,
)

data class OrderInfoResponse(
    val id: Long,
    val user: User,
    val restaurant: Restaurant,
    val items: List<OrderItemResponse>
)

interface OrderInfoProjection {
    val id: Long
    val user: User
    val restaurant: Restaurant
    val orderItems: List<OrderItemInfo>

    interface OrderItemInfo {
        val item: MenuInfo
        val quantity: Int
    }

    interface MenuInfo {
        val id: Long
        val name: String
        val price: Double
    }
}