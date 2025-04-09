package com.coded.spring.ordering.respositories

import com.coded.spring.ordering.domain.entities.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository: JpaRepository<OrderItem, Long?>
