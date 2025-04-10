package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection


interface MenuService {
    fun findAll(): List<Menu>
    fun create(menuItem: Menu): Menu
    fun findById(id: Long): Menu?
    fun findAllIn(items: List<Long>): List<Menu>
    fun findByRestaurantId(restaurantId: Long): List<MenuBasicInfoProjection>
    fun getMenusInRequestOrder(menuIds: List<Long>): List<Menu>
}