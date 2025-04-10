package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.domain.entities.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository: JpaRepository<Restaurant, Long> {
    fun findByName(name: String): Restaurant?
}