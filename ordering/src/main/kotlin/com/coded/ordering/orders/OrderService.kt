package com.coded.ordering.orders

import com.coded.ordering.orders.dtos.OrderCreateDto
import com.coded.ordering.domain.entities.OrderEntity
import com.coded.ordering.domain.projections.OrderInfoProjection
import com.coded.ordering.orders.dtos.OrderCreateResponse

interface OrderService {
    fun findAll(): List<OrderInfoProjection>
    fun create(newOrder: OrderCreateDto): OrderCreateResponse
    fun findById(id: Long): OrderEntity?
    fun getAllOrdersByUserId(userId: Long): List<OrderInfoProjection>
}