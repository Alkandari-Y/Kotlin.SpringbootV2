package com.coded.spring.ordering.orders.dtos


interface OrderResponseSummary {
    val id: Long
    fun getUserName(): String
    fun getOrderItemsMenuItemsName(): List<String>
}
