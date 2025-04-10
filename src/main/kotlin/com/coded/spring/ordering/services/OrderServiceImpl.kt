package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.dtos.OrderCreateDto
import com.coded.spring.ordering.domain.dtos.toEntity
import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.entities.OrderItem
import com.coded.spring.ordering.domain.projections.OrderInfoProjection
import com.coded.spring.ordering.domain.projections.OrderInfoResponse
import com.coded.spring.ordering.domain.projections.OrderItemResponse
import com.coded.spring.ordering.repositories.MenuRepository
import com.coded.spring.ordering.repositories.OrderItemRepository
import com.coded.spring.ordering.repositories.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val menuRepository: MenuRepository,
) : OrderService {
    override fun findAll(): List<Order> = orderRepository.findAll()

    @Transactional
    override fun create(
        newOrder: OrderCreateDto
    ) {
        val menuIds = newOrder.items.map { it.itemId }
        val foundMenus = menuRepository.findAllByIdIn(menuIds)

        val foundIds = foundMenus.mapNotNull { it.id }.toSet()
        val missingIds = menuIds.filterNot { it in foundIds }
        if (missingIds.isNotEmpty()) {
            throw IllegalStateException("Menus not found: $missingIds")
        }

        val order: Order = orderRepository.save(newOrder.toEntity())
        val orderItems = newOrder.items.map { itemDto ->
            val menu = foundMenus.find { menu -> menu.id == itemDto.itemId }
                ?: throw IllegalStateException("Menu not found for id: ${itemDto.itemId}")
            OrderItem(
                item = menu,
                order = order,
                quantity = itemDto.quantity
            )
        }
        orderItemRepository.saveAll(orderItems)
    }

    override fun findById(id: Long): Order? = orderRepository.findByIdOrNull(id)
    override fun getAllOrders(): List<OrderInfoProjection> = orderRepository.findAllProjectedBy()
}