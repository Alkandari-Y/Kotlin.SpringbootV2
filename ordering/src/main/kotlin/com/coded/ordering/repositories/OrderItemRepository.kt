package com.coded.ordering.repositories

import com.coded.ordering.domain.entities.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderItemRepository: JpaRepository<OrderItemEntity, Long> {
}