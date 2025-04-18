package com.coded.spring.ordering.domain.projections

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.entities.UserEntity
import java.math.BigDecimal

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
    val user: UserEntity,
    val restaurant: RestaurantEntity,
    val items: List<OrderItemResponse>
)

interface OrderInfoProjection {
    val id: Long
    val user: UserInfo
    val restaurant: RestaurantEntity
    val orderItems: List<OrderItemInfo>

    interface UserInfo {
        val username: String
        val email: String
        val id: Long
    }

    interface OrderItemInfo {
        val item: MenuInfo
        val quantity: Int
    }

    interface MenuInfo {
        val id: Long
        val name: String
        val price: BigDecimal
    }
}