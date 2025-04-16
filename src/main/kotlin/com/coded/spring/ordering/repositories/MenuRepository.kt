package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.MenuEntity
import com.coded.spring.ordering.domain.projections.MenuBasicInfoProjection
import com.coded.spring.ordering.domain.projections.MenuInfoSearchProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository: JpaRepository<MenuEntity, Long> {

    fun findByRestaurant_Id(restaurantId: Long): List<MenuBasicInfoProjection>
    fun findAllByIdIn(menuIds: List<Long>): List<MenuEntity>

    @Query("""
        SELECT m FROM MenuEntity m
        WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :menuName, '%'))
        AND LOWER(m.restaurant.name) LIKE LOWER(CONCAT('%', :restName, '%'))  
    """)
    fun searchByMenuAndRestaurantName(
        @Param("menuName") menuName: String,
        @Param("restName") restName: String
    ): List<MenuInfoSearchProjection>

    fun findByNameContainingIgnoreCase(name: String)
    : List<MenuInfoSearchProjection>

    @Query("""
        SELECT m FROM MenuEntity m 
        WHERE LOWER(m.restaurant.name) LIKE LOWER(CONCAT('%', :restName, '%'))
    """)

    fun findByRestaurantNameContainingIgnoreCase(
        @Param("restName") restName: String
    ): List<MenuInfoSearchProjection>
}