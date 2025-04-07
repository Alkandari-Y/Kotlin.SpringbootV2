package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.domain.dtos.OrderCreateRequestDto
import com.coded.spring.ordering.domain.dtos.OrderDto
import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.entities.OrderItemRepository
import com.coded.spring.ordering.domain.entities.OrderRepository
import com.coded.spring.ordering.domain.toDto
import com.coded.spring.ordering.domain.toEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
){

    @GetMapping
    fun getOrders(): List<OrderDto> = orderRepository.findAll().map { it.toDto() }

    @PostMapping
    fun createOrder(@RequestBody orderCreateRequestDto: OrderCreateRequestDto): OrderDto {
        val orderEntity = orderCreateRequestDto.toEntity()
        val order = orderRepository.save(orderEntity)
        val items = orderItemRepository.saveAll(orderEntity.items.map { it.copy(order = order) })

        return order.copy(items = items).toDto()
    }

}