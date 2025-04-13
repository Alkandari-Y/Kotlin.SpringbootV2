package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository: JpaRepository<Restaurant, Long> {
//    @Query("SELECT r FROM Restaurant r")
//    fun allRestaurants(): List<RestaurantInfoProjection>

    fun findByName(name: String): Restaurant?

    @Query("SELECT r FROM Restaurant r")
    fun details(): List<RestaurantInfoProjection>
}