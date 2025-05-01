package com.coded.ordering.repositories

import com.coded.ordering.domain.entities.OrderEntity
import com.coded.ordering.domain.projections.OrderInfoProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository: JpaRepository<OrderEntity, Long> {
    @Query("SELECT o FROM OrderEntity o")
    fun findAllProjectedBy(): List<OrderInfoProjection>

    @Query("SELECT o FROM OrderEntity o WHERE o.userId = :userId")
    fun findAllByUserId(@Param("userId") userId: Long): List<OrderInfoProjection>
}