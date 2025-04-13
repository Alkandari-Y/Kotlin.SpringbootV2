package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection

interface RestaurantService {
    fun findAll(): List<Restaurant>
    fun getInto(): List<RestaurantInfoProjection>
    fun create(restaurant: Restaurant): Restaurant
    fun findById(id: Long): Restaurant?
    fun findByName(name: String): Restaurant?
}