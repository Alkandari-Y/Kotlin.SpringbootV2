package com.coded.spring.ordering.respositories

import com.coded.spring.ordering.domain.projections.RestaurantSummary
import com.coded.spring.ordering.domain.entities.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository: JpaRepository<Restaurant, Long?> {

    @Query("SELECT (r.id, r.name) FROM Restaurant r")
    fun all(): List<RestaurantSummary>

    fun findByName(name: String): Restaurant?
}