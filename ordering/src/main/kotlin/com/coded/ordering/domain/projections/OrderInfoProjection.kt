package com.coded.ordering.domain.projections

import com.coded.ordering.domain.entities.RestaurantEntity
import java.math.BigDecimal



interface OrderInfoProjection {
    val id: Long
    val userId: Long
    val restaurant: RestaurantEntity
    val orderItems: List<OrderItemInfo>

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