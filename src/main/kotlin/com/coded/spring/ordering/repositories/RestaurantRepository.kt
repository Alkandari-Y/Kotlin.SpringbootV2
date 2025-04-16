package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository: JpaRepository<RestaurantEntity, Long> {

    fun findByName(name: String): RestaurantEntity?

    @Query("SELECT r FROM RestaurantEntity r")
    fun details(): List<RestaurantInfoProjection>
}