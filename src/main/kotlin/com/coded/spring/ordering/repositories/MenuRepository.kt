package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.Menu
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository: JpaRepository<Menu, Long> {
    fun findByRestaurant_Id(restaurantId: Long): List<MenuBasicInfoProjection>
    fun findAllByIdIn(menuIds: List<Long>): List<Menu>
}