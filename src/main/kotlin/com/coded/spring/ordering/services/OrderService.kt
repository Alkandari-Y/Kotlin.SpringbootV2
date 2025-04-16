package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.dtos.OrderCreateDto
import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.projections.OrderInfoProjection

interface OrderService {
    fun findAll(): List<OrderInfoProjection>
    fun create(newOrder: OrderCreateDto)
    fun findById(id: Long): Order?
    fun getAllOrders(): List<OrderInfoProjection>
}