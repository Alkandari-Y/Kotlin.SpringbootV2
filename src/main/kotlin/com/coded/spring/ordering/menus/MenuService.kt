package com.coded.spring.ordering.menus

import com.coded.spring.ordering.menus.dtos.MenuDetailResponse
import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection


interface MenuService {
    fun findAll(): List<MenuEntity>
    fun create(menuItem: MenuEntity): MenuEntity
    fun findById(id: Long): MenuDetailResponse?
    fun findAllIn(items: List<Long>): List<MenuEntity>
    fun findByRestaurantId(restaurantId: Long): List<MenuBasicInfoProjection>
    fun getMenusInRequestOrder(menuIds: List<Long>): List<MenuEntity>
    fun searchMenus(menuName: String?=null, restName: String?)
        : List<MenuInfoSearchProjection>
}