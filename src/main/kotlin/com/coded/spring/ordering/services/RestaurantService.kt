package com.coded.spring.ordering.services

import com.coded.spring.ordering.domain.entities.Restaurant

interface RestaurantService {
    fun findAll(): List<Restaurant>
    fun create(restaurant: Restaurant): Restaurant
    fun findById(id: Long): Restaurant?
    fun findByName(name: String): Restaurant?
}