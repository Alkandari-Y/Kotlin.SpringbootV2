package com.coded.spring.ordering.domain.dtos

import com.coded.spring.ordering.domain.entities.Order
import org.springframework.data.jpa.repository.JpaRepository


interface OrderResponseSummary {
    val id: Long
    fun getUserName(): String
    fun getOrderItemsMenuItemsName(): List<String>
}
