package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.dtos.MenuDetailResponse
import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection


interface MenuService {
    fun findAll(): List<Menu>
    fun create(menuItem: Menu): Menu
    fun findById(id: Long): MenuDetailResponse?
    fun findAllIn(items: List<Long>): List<Menu>
    fun findByRestaurantId(restaurantId: Long): List<MenuBasicInfoProjection>
    fun getMenusInRequestOrder(menuIds: List<Long>): List<Menu>
    fun searchMenus(menuName: String?=null, restName: String?)
        : List<MenuInfoSearchProjection>
}