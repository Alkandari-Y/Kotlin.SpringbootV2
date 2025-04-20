package com.coded.spring.ordering.restaurants

import com.coded.spring.ordering.domain.entities.RestaurantEntity
import com.coded.spring.ordering.domain.projections.RestaurantInfoProjection

interface RestaurantService {
    fun findAll(): List<RestaurantEntity>
    fun getInto(): List<RestaurantInfoProjection>
    fun create(restaurant: RestaurantEntity): RestaurantEntity
    fun findById(id: Long): RestaurantEntity?
    fun findByName(name: String): RestaurantEntity?
}