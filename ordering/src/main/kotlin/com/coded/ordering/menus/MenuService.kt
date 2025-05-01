package com.coded.ordering.menus

import com.coded.ordering.menus.dtos.MenuDetailResponse
import com.coded.ordering.domain.entities.MenuEntity
import com.coded.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.ordering.domain.projections.MenuInfoSearchProjection


interface MenuService {
    fun findAll(): List<MenuDetailResponse>
    fun create(menuItem: MenuEntity): MenuEntity
    fun findById(id: Long): MenuDetailResponse?
    fun findAllIn(items: List<Long>): List<MenuEntity>
    fun findByRestaurantId(restaurantId: Long): List<MenuBasicInfoProjection>
    fun getMenusInRequestOrder(menuIds: List<Long>): List<MenuEntity>
    fun searchMenus(menuName: String?=null, restName: String?)
        : List<MenuInfoSearchProjection>
}