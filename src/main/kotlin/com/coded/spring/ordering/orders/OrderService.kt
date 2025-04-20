package com.coded.spring.ordering.orders

import com.coded.spring.ordering.orders.dtos.OrderCreateDto
import com.coded.spring.ordering.domain.entities.OrderEntity
import com.coded.spring.ordering.domain.projections.OrderInfoProjection

interface OrderService {
    fun findAll(): List<OrderInfoProjection>
    fun create(newOrder: OrderCreateDto)
    fun findById(id: Long): OrderEntity?
    fun getAllOrders(): List<OrderInfoProjection>
}