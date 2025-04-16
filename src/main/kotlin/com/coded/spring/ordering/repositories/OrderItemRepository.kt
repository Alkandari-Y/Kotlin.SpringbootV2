package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderItemRepository: JpaRepository<OrderItemEntity, Long> {
}