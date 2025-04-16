package com.coded.spring.ordering.domain.dtos


interface OrderResponseSummary {
    val id: Long
    fun getUserName(): String
    fun getOrderItemsMenuItemsName(): List<String>
}
