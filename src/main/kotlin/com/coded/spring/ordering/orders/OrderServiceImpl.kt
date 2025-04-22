package com.coded.spring.ordering.orders

import com.coded.spring.ordering.domain.entities.OrderEntity
import com.coded.spring.ordering.domain.entities.OrderItemEntity
import com.coded.spring.ordering.domain.projections.OrderInfoProjection
import com.coded.spring.ordering.orders.dtos.*
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
    override fun findAll(): List<OrderInfoProjection> = orderRepository.findAllProjectedBy()

    @Transactional
    override fun create(
        newOrder: OrderCreateDto
    ): OrderCreateResponse {
        val menuIds = newOrder.items.map { it.itemId }
        val foundMenus = menuRepository.findAllByIdIn(menuIds)

        val foundIds = foundMenus.mapNotNull { it.id }.toSet()
        val missingIds = menuIds.filterNot { it in foundIds }
        if (missingIds.isNotEmpty()) {
            throw IllegalStateException("Menus not found: $missingIds")
        }

        val order: OrderEntity = orderRepository.save(newOrder.toOrderEntity())
        val orderItems = orderItemRepository.saveAll(
                newOrder.items.map { itemDto ->
                val menu = foundMenus.find { menu -> menu.id == itemDto.itemId }
                    ?: throw IllegalStateException("Menu not found for id: ${itemDto.itemId}")
                OrderItemEntity(
                    item = menu,
                    order = order,
                    quantity = itemDto.quantity
                )
            }
        )

        return OrderCreateResponse(
            id = order.id!!,
            userId = newOrder.user.id!!,
            restaurantId = newOrder.restaurant.id!!,
            items = orderItems.map { it ->
                OrderItemResponse(
                    id = it.id!!,
                    item = foundMenus.find { menu -> it.item?.id == menu.id }!!.toItemResponse(),
                    quantity = it.quantity!!
                )
            }
        )


    }

    override fun findById(id: Long): OrderEntity? = orderRepository.findByIdOrNull(id)
    override fun getAllOrders(): List<OrderInfoProjection> = orderRepository.findAllProjectedBy()
}