package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Restaurant
import com.coded.spring.ordering.domain.projections.RestaurantSummary


interface RestaurantService {
    fun save(restaurant: Restaurant): Restaurant
    fun allSummaries(): Iterable<RestaurantSummary>
    fun getById(id: Long): Restaurant?
    fun getByName(name: String): Restaurant?
    fun create(restaurant: Restaurant): Restaurant
}