package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.Order
import com.coded.spring.ordering.domain.projections.OrderInfoProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository: JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o")
    fun findAllProjectedBy(): List<OrderInfoProjection>

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    fun findProjectedById(@Param("id") id: Long): OrderInfoProjection?
}